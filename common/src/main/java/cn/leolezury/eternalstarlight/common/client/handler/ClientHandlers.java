package cn.leolezury.eternalstarlight.common.client.handler;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.client.shader.ESShaders;
import cn.leolezury.eternalstarlight.common.entity.living.boss.LunarMonstrosity;
import cn.leolezury.eternalstarlight.common.entity.living.boss.gatekeeper.TheGatekeeper;
import cn.leolezury.eternalstarlight.common.entity.living.boss.golem.StarlightGolem;
import cn.leolezury.eternalstarlight.common.entity.misc.CameraShake;
import cn.leolezury.eternalstarlight.common.platform.ESPlatform;
import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import cn.leolezury.eternalstarlight.common.registry.ESFluids;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import cn.leolezury.eternalstarlight.common.util.ESBlockUtil;
import cn.leolezury.eternalstarlight.common.util.ESEntityUtil;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import java.util.*;

@Environment(EnvType.CLIENT)
public class ClientHandlers {
    public static final Set<Mob> BOSSES = Collections.newSetFromMap(new WeakHashMap<>());
    private static final ResourceLocation[] BAR_BACKGROUND_SPRITES = new ResourceLocation[]{new ResourceLocation("boss_bar/pink_background"), new ResourceLocation("boss_bar/blue_background"), new ResourceLocation("boss_bar/red_background"), new ResourceLocation("boss_bar/green_background"), new ResourceLocation("boss_bar/yellow_background"), new ResourceLocation("boss_bar/purple_background"), new ResourceLocation("boss_bar/white_background")};
    private static final ResourceLocation[] BAR_PROGRESS_SPRITES = new ResourceLocation[]{new ResourceLocation("boss_bar/pink_progress"), new ResourceLocation("boss_bar/blue_progress"), new ResourceLocation("boss_bar/red_progress"), new ResourceLocation("boss_bar/green_progress"), new ResourceLocation("boss_bar/yellow_progress"), new ResourceLocation("boss_bar/purple_progress"), new ResourceLocation("boss_bar/white_progress")};
    private static final ResourceLocation[] OVERLAY_BACKGROUND_SPRITES = new ResourceLocation[]{new ResourceLocation("boss_bar/notched_6_background"), new ResourceLocation("boss_bar/notched_10_background"), new ResourceLocation("boss_bar/notched_12_background"), new ResourceLocation("boss_bar/notched_20_background")};
    private static final ResourceLocation[] OVERLAY_PROGRESS_SPRITES = new ResourceLocation[]{new ResourceLocation("boss_bar/notched_6_progress"), new ResourceLocation("boss_bar/notched_10_progress"), new ResourceLocation("boss_bar/notched_12_progress"), new ResourceLocation("boss_bar/notched_20_progress")};
    private static final ResourceLocation ETHER_EROSION_OVERLAY = new ResourceLocation(EternalStarlight.MOD_ID, "textures/misc/ether_erosion.png");
    private static final ResourceLocation ETHER_ARMOR_EMPTY = new ResourceLocation(EternalStarlight.MOD_ID, "textures/gui/hud/ether_armor_empty.png");
    private static final ResourceLocation ETHER_ARMOR_HALF = new ResourceLocation(EternalStarlight.MOD_ID, "textures/gui/hud/ether_armor_half.png");
    private static final ResourceLocation ETHER_ARMOR_FULL = new ResourceLocation(EternalStarlight.MOD_ID, "textures/gui/hud/ether_armor_full.png");
    private static final ResourceLocation ORB_OF_PROPHECY_USE = new ResourceLocation(EternalStarlight.MOD_ID, "textures/misc/orb_of_prophecy_use.png");
    private static final List<DreamCatcherText> DREAM_CATCHER_TEXTS = new ArrayList<>();

    public static void onClientTick() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(ESMobEffects.DREAM_CATCHER.get())) {
            List<DreamCatcherText> toRemove = new ArrayList<>();
            for (DreamCatcherText text : DREAM_CATCHER_TEXTS) {
                text.updatePosition();
                int width = Minecraft.getInstance().font.width(text.getText());
                int height = Minecraft.getInstance().font.lineHeight;
                if (text.getX() - width / 2 > Minecraft.getInstance().getWindow().getGuiScaledWidth() || text.getY() - height / 2 > Minecraft.getInstance().getWindow().getGuiScaledHeight()) {
                    toRemove.add(text);
                }
            }
            for (DreamCatcherText text : toRemove) {
                DREAM_CATCHER_TEXTS.remove(text);
            }
            if (player.getRandom().nextInt(40) == 0 && DREAM_CATCHER_TEXTS.size() < 20) {
                Component component = Component.translatable("message." + EternalStarlight.MOD_ID + ".dream_catcher_" + player.getRandom().nextInt(7));
                DREAM_CATCHER_TEXTS.add(new DreamCatcherText(component, (int) (player.getRandom().nextFloat() * 5 + 1), -Minecraft.getInstance().font.width(component) / 2, player.getRandom().nextInt(Minecraft.getInstance().getWindow().getGuiScaledHeight())));
            }
        } else {
            DREAM_CATCHER_TEXTS.clear();
        }
    }

    public static Vec3 computeCameraAngles(Vec3 angles) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        float partialTicks = Minecraft.getInstance().getFrameTime();

        if (localPlayer != null) {
            float ticks = localPlayer.tickCount + partialTicks;
            if (!Minecraft.getInstance().isPaused()) {
                float shakeAmount = 0;
                if (Minecraft.getInstance().level != null) {
                    for (CameraShake cameraShake : Minecraft.getInstance().level.getEntitiesOfClass(CameraShake.class, localPlayer.getBoundingBox().inflate(50))) {
                        if (cameraShake.distanceTo(localPlayer) < cameraShake.getRadius()) {
                            shakeAmount += cameraShake.getShakeAmount(localPlayer, partialTicks);
                        }
                    }
                }
                shakeAmount = Math.min(shakeAmount, 1);

                return new Vec3(angles.x + shakeAmount * Math.cos(ticks * 3) * 20, angles.y + shakeAmount * Math.cos(ticks * 4) * 20, angles.z + shakeAmount * Math.cos(ticks * 5) * 20);
            }
        }
        return angles;
    }

    // tail of setupFog
    public static void onRenderFog(Camera camera) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null) {
            return;
        }
        if (ESPlatform.INSTANCE.getLoader() == ESPlatform.Loader.FABRIC) {
            FluidState fluidState = camera.getEntity().level().getFluidState(camera.getBlockPosition());
            if (fluidState.is(ESFluids.ETHER_STILL.get()) || fluidState.is(ESFluids.ETHER_FLOWING.get())) {
                if (camera.getPosition().y < (double) ((float) camera.getBlockPosition().getY() + fluidState.getHeight(camera.getEntity().level(), camera.getBlockPosition()))) {
                    RenderSystem.setShaderFogStart(0.0F);
                    RenderSystem.setShaderFogEnd(3.0F);
                    RenderSystem.setShaderFogColor(232 / 255F, 255 / 255F, 222 / 255F);
                    RenderSystem.setShaderFogShape(FogShape.SPHERE);
                }
            }
        }
        /*Holder<Biome> biomeHolder = player.level().getBiome(new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()));
        boolean noFluidAtCam = player.level().getBlockState(camera.getBlockPosition()).getFluidState().isEmpty();
        if (camera.getFluidInCamera() == FogType.NONE && noFluidAtCam) {
            if (biomeHolder.is(ESTags.Biomes.PERMAFROST_FOREST_VARIANT)) {
                RenderSystem.setShaderFogStart(-4.0F);
                RenderSystem.setShaderFogEnd(96.0F);
                RenderSystem.setShaderFogColor(0.87f, 0.87f, 1f);
            } else if (biomeHolder.is(ESTags.Biomes.DARK_SWAMP_VARIANT)) {
                RenderSystem.setShaderFogStart(-1.0F);
                RenderSystem.setShaderFogEnd(96.0F);
                RenderSystem.setShaderFogColor(0.07f, 0, 0.07f);
            }
            RenderSystem.setShaderFogShape(FogShape.SPHERE);
        }*/
    }

    public static boolean renderBossBar(GuiGraphics guiGraphics, LerpingBossEvent bossEvent, int x, int y) {
        ResourceLocation barLocation;
        Mob boss = null;
        if (BOSSES.isEmpty()) {
            return false;
        }
        for (Mob mob : BOSSES) {
            if (mob.getUUID().equals(bossEvent.getId())) {
                boss = mob;
                break;
            }
        }
        if (boss == null) {
            return false;
        }
        //Component bossDesc;
        if (boss instanceof TheGatekeeper) {
            barLocation = new ResourceLocation(EternalStarlight.MOD_ID,"textures/gui/bars/the_gatekeeper.png");
            //bossDesc = Component.translatable("boss." + EternalStarlight.MOD_ID + ".the_gatekeeper.desc");
        } else if (boss instanceof StarlightGolem) {
            barLocation = new ResourceLocation(EternalStarlight.MOD_ID,"textures/gui/bars/starlight_golem.png");
            //bossDesc = Component.translatable("boss." + EternalStarlight.MOD_ID + ".starlight_golem.desc");
        } else if (boss instanceof LunarMonstrosity) {
            barLocation = new ResourceLocation(EternalStarlight.MOD_ID,"textures/gui/bars/lunar_monstrosity.png");
            //bossDesc = Component.translatable("boss." + EternalStarlight.MOD_ID + ".lunar_monstrosity.desc");
        } else {
            return false;
        }
        //event.setIncrement(12 + 2 * Minecraft.getInstance().font.lineHeight);
        drawBar(guiGraphics, x, y, bossEvent, barLocation);
        Component component = bossEvent.getName();
        int textWidth = Minecraft.getInstance().font.width(component);
        int textX = Minecraft.getInstance().getWindow().getGuiScaledWidth() / 2 - textWidth / 2;
        int textY = y - 9;
        guiGraphics.drawString(Minecraft.getInstance().font, component, textX, textY, 16777215);
        /*int descWidth = Minecraft.getInstance().font.width(bossDesc);
        int descX = Minecraft.getInstance().getWindow().getGuiScaledWidth() / 2 - descWidth / 2;
        int descY = event.getY() + 9;
        event.getGuiGraphics().drawString(Minecraft.getInstance().font, bossDesc, descX, descY, 16777215);*/
        return true;
    }

    public static void drawBar(GuiGraphics guiGraphics, int x, int y, BossEvent event, ResourceLocation barLocation) {
        drawBar(guiGraphics, x, y, event, 182, BAR_BACKGROUND_SPRITES, OVERLAY_BACKGROUND_SPRITES);
        int k = Mth.lerpDiscrete(event.getProgress(), 0, 182);
        if (k > 0) {
            drawBar(guiGraphics, x, y, event, k, BAR_PROGRESS_SPRITES, OVERLAY_PROGRESS_SPRITES);
        }
        guiGraphics.blit(barLocation, x - 36, y - 32, 0.0F, 0.0F, 256, 64, 256, 64);
    }

    private static void drawBar(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, int progress, ResourceLocation[] bars, ResourceLocation[] overlays) {
        guiGraphics.blitSprite(bars[bossEvent.getColor().ordinal()], 182, 5, 0, 0, x, y, progress, 5);
        if (bossEvent.getOverlay() != BossEvent.BossBarOverlay.PROGRESS) {
            RenderSystem.enableBlend();
            guiGraphics.blitSprite(overlays[bossEvent.getOverlay().ordinal() - 1], 182, 5, 0, 0, x, y, progress, 5);
            RenderSystem.disableBlend();
        }
    }

    public static void renderEtherErosion(Gui gui, GuiGraphics guiGraphics) {
        float clientEtherTicksRaw = ESEntityUtil.getPersistentData(Minecraft.getInstance().player).getInt("ClientEtherTicks");
        float clientEtherTicks = Math.min(clientEtherTicksRaw + Minecraft.getInstance().getFrameTime(), 140f);
        float erosionProgress = Math.min(clientEtherTicks, 140f) / 140f;
        if (clientEtherTicksRaw > 0) {
            gui.renderTextureOverlay(guiGraphics, ETHER_EROSION_OVERLAY, erosionProgress);
        }
    }

    public static void renderEtherArmor(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player.level().isClientSide && ESBlockUtil.isEntityInBlock(minecraft.player, ESBlocks.ETHER.get())) {
            minecraft.getProfiler().push("armor");
            int initialX = screenWidth / 2 - 91;
            int initialY = screenHeight - 39;
            float maxHealth = (float) Math.max(minecraft.player.getAttributeValue(Attributes.MAX_HEALTH), Mth.ceil(minecraft.player.getHealth()));
            int absorptionAmount = Mth.ceil(minecraft.player.getAbsorptionAmount());
            int q = Mth.ceil((maxHealth + (float) absorptionAmount) / 2.0F / 10.0F);
            int r = Math.max(10 - (q - 2), 3);
            int armorValue = minecraft.player.getArmorValue();
            int y = initialY - (q - 1) * r - 10;
            for (int i = 0; i < 10; ++i) {
                if (armorValue > 0) {
                    int x = initialX + i * 8;
                    if (i * 2 + 1 < armorValue) {
                        guiGraphics.blit(ETHER_ARMOR_FULL, x, y, 0f, 0f, 9, 9, 9, 9);
                    }

                    if (i * 2 + 1 == armorValue) {
                        guiGraphics.blit(ETHER_ARMOR_HALF, x, y, 0f, 0f, 9, 9, 9, 9);
                    }

                    if (i * 2 + 1 > armorValue) {
                        guiGraphics.blit(ETHER_ARMOR_EMPTY, x, y, 0f, 0f, 9, 9, 9, 9);
                    }
                }
            }
        }
    }

    public static void renderOrbOfProphecyUse(Gui gui, GuiGraphics guiGraphics) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            int usingTicks = player.getTicksUsingItem();
            float ticks = Math.min(usingTicks + Minecraft.getInstance().getFrameTime(), 150f);
            float progress = Math.min(ticks, 150f) / 150f;
            if (player.isUsingItem() && player.getUseItem().is(ESItems.ORB_OF_PROPHECY.get())) {
                if (usingTicks < 150) {
                    gui.renderTextureOverlay(guiGraphics, ORB_OF_PROPHECY_USE, progress);
                }
            }
        }
    }

    public static void renderDreamCatcher(GuiGraphics guiGraphics) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(ESMobEffects.DREAM_CATCHER.get())) {
            for (DreamCatcherText text : DREAM_CATCHER_TEXTS) {
                guiGraphics.drawCenteredString(Minecraft.getInstance().font, text.getText(), text.getX(), text.getY(), 0x5187c4);
            }
        }
    }

    private static class DreamCatcherText {
        private final Component text;
        private final int speed;
        private int x;
        private final int y;

        public DreamCatcherText(Component component, int speed, int x, int y) {
            this.text = component;
            this.speed = speed;
            this.x = x;
            this.y = y;
        }

        public Component getText() {
            return text;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void updatePosition() {
            x += speed;
        }
    }

    public static void renderCrystallineInfection(GuiGraphics guiGraphics) {
        LocalPlayer player = Minecraft.getInstance().player;
        Window window = Minecraft.getInstance().getWindow();
        ShaderInstance shader = ESShaders.getCrystallineInfection();
        int x = window.getGuiScaledWidth();
        int y = window.getGuiScaledHeight();
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        if (player != null && player.hasEffect(ESMobEffects.CRYSTALLINE_INFECTION.get())) {
            Uniform randomFloat = shader.getUniform("RandomFloat");
            if (randomFloat != null) {
                randomFloat.set(new Random().nextFloat(0.0F, 3.0F));
            }
            RenderSystem.setShader(ESShaders::getCrystallineInfection);
            BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.vertex(matrix4f, 0, 0, 0).uv(0, 0).endVertex();
            bufferBuilder.vertex(matrix4f, 0, y, 0).uv(0, 1).endVertex();
            bufferBuilder.vertex(matrix4f, x, y, 0).uv(1, 1).endVertex();
            bufferBuilder.vertex(matrix4f, x, 0, 0).uv(1, 0).endVertex();
            BufferUploader.drawWithShader(bufferBuilder.end());
        }
    }
}
