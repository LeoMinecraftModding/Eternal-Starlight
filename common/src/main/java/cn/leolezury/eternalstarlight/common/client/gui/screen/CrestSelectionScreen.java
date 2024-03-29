package cn.leolezury.eternalstarlight.common.client.gui.screen;

import cn.leolezury.eternalstarlight.common.client.gui.screen.widget.CrestButton;
import cn.leolezury.eternalstarlight.common.client.gui.screen.widget.CrestPageButton;
import cn.leolezury.eternalstarlight.common.client.shader.ESShaders;
import cn.leolezury.eternalstarlight.common.crest.Crest;
import cn.leolezury.eternalstarlight.common.data.ESRegistries;
import cn.leolezury.eternalstarlight.common.network.UpdateCrestsPacket;
import cn.leolezury.eternalstarlight.common.platform.ESPlatform;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class CrestSelectionScreen extends Screen {
    private final List<CrestButton> crestButtons = new ArrayList<>();
    private final List<Crest> ownedCrests;
    private List<String> crestIds;
    private CrestButton selectedCrestButton;
    private CrestPageButton nextPage;
    private CrestPageButton previousPage;
    private Crest selectedCrest;
    private int selected;
    private int tickCount;

    public CrestSelectionScreen(List<String> ownedCrests, List<String> crests) {
        super(Component.empty());
        Registry<Crest> registry;
        if (Minecraft.getInstance().level != null) {
            registry = Minecraft.getInstance().level.registryAccess().registryOrThrow(ESRegistries.CREST);
        } else {
            registry = null;
        }
        this.ownedCrests = ownedCrests.stream().map(s -> registry == null ? null : registry.get(new ResourceLocation(s))).sorted((o1, o2) -> registry == null ? 0 : registry.getId(o1) - registry.getId(o2)).toList();
        this.crestIds = crests;
    }

    @Override
    protected void init() {
        if (Minecraft.getInstance().level != null) {
            Registry<Crest> registry = Minecraft.getInstance().level.registryAccess().registryOrThrow(ESRegistries.CREST);
            this.previousPage = this.addRenderableWidget(new CrestPageButton(this.width / 4 - 24, this.height / 2 - 12 - 50, 48, 24, false, Component.empty(), (button -> previousPage())));
            this.nextPage = this.addRenderableWidget(new CrestPageButton(this.width / 4 - 24, this.height / 2 - 12 + 50, 48, 24, true, Component.empty(), (button -> nextPage())));
            List<Crest> crests = crestIds == null ? this.crestButtons.stream().map(CrestButton::getCrest).toList() : crestIds.stream().map(s -> registry.get(new ResourceLocation(s))).toList();
            this.crestButtons.clear();
            for (int i = 0; i < 5; i++) {
                int ordinal = i;
                CrestButton crestButton = this.addRenderableWidget(new CrestButton((int) ((this.width / 9f) * 5f), (int) (this.height / 2f), 72, 72, true, Component.empty(), (button -> cancelCrest(ordinal))).setCrest(i >= crests.size() ? null : crests.get(i)));
                this.crestButtons.add(crestButton);
            }
            this.selectedCrestButton = this.addRenderableWidget(new CrestButton(this.width / 4 - 36, this.height / 2 - 36, 72, 72, Component.empty(), (button -> selectCrest())));
            updateGui();
            crestIds = null;
        } else {
            onClose();
        }
    }

    @Override
    public void tick() {
        for (CrestButton button : crestButtons) {
            button.tick();
        }
        selectedCrestButton.tick();
        nextPage.tick();
        previousPage.tick();
        updateGui();
        tickCount++;
    }

    public void updateGui() {
        if (!ownedCrests.isEmpty()) {
            this.nextPage.active = selected < ownedCrests.size() - 1;
            this.previousPage.active = selected > 0;
            this.selectedCrest = ownedCrests.get(selected);
            this.selectedCrestButton.setCrest(selectedCrest);
        } else {
            this.nextPage.active = false;
            this.previousPage.active = false;
            this.selectedCrest = null;
            this.selectedCrestButton.setCrest(null);
            this.selectedCrestButton.active = false;
        }
        List<CrestButton> notEmptyButtons = crestButtons.stream().filter((crestButton -> !crestButton.isEmpty())).toList();
        for (int n = 0; n < notEmptyButtons.size(); n++) {
            CrestButton crestButton = crestButtons.get(n);
            crestButton.setOrbitCenter((this.width / 9f) * 5f, this.height / 2f);
            crestButton.setAngle((360f / notEmptyButtons.size()) * n + tickCount);
        }
    }

    private void previousPage() {
        if (selected > 0) {
            selected--;
            updateGui();
        }
    }

    private void nextPage() {
        if (selected < ownedCrests.size() - 1) {
            selected++;
            updateGui();
        }
    }

    private void cancelCrest(int ordinal) {
        if (!crestButtons.get(ordinal).isEmpty()) {
            List<Crest> crests = new ArrayList<>();
            crestButtons.get(ordinal).setCrest(null);
            for (CrestButton crestButton : crestButtons) {
                if (!crestButton.isEmpty()) {
                    crests.add(crestButton.getCrest());
                }
            }
            for (int i = 0; i < crestButtons.size(); i++) {
                CrestButton crestButton = crestButtons.get(i);
                crestButton.setCrest(i >= crests.size() ? null : crests.get(i));
            }
        }
    }
    
    private void selectCrest() {
        if (crestButtons.stream().anyMatch((crestButton -> !crestButton.isEmpty() && crestButton.getCrest().type() == selectedCrest.type()))) {
            return;
        }
        for (CrestButton crestButton : crestButtons) {
            if (crestButton.isEmpty()) {
                crestButton.setCrest(selectedCrest);
                return;
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        Minecraft client = Minecraft.getInstance();
        Window window = client.getWindow();
        int x = window.getGuiScaledWidth();
        int y = window.getGuiScaledHeight();
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        ShaderInstance instance = ESShaders.getCrestSelectionGui();
        if (instance != null) {
            Uniform tickUniform = instance.getUniform("TickCount");
            Uniform ratioUniform = instance.getUniform("Ratio");
            if (tickUniform != null) {
                tickUniform.set((float) tickCount + Minecraft.getInstance().getFrameTime());
            }
            if (ratioUniform != null) {
                ratioUniform.set((float) y / x);
            }
        }
        RenderSystem.setShader(ESShaders::getCrestSelectionGui);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, 0, 0, 0).uv(0, 0).endVertex();
        bufferBuilder.vertex(matrix4f, 0, y, 0).uv(0, 1).endVertex();
        bufferBuilder.vertex(matrix4f, x, y, 0).uv(1, 1).endVertex();
        bufferBuilder.vertex(matrix4f, x, 0, 0).uv(1, 0).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {

    }

    @Override
    public void onClose() {
        super.onClose();
        if (Minecraft.getInstance().level != null) {
            Registry<Crest> registry = Minecraft.getInstance().level.registryAccess().registryOrThrow(ESRegistries.CREST);
            List<String> crests = new ArrayList<>();
            for (CrestButton button : crestButtons) {
                if (!button.isEmpty()) {
                    crests.add(Objects.requireNonNull(registry.getKey(button.getCrest())).toString());
                }
            }
            ESPlatform.INSTANCE.sendToServer(new UpdateCrestsPacket(crests));
        }
    }
}
