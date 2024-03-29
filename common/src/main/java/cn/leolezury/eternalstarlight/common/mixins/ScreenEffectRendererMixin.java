package cn.leolezury.eternalstarlight.common.mixins;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import cn.leolezury.eternalstarlight.common.util.ESBlockUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public abstract class ScreenEffectRendererMixin {
    @Unique
    private static final Material ABYSSAL_FIRE_1 = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(EternalStarlight.MOD_ID, "block/abyssal_fire_1"));

    @Inject(method = "renderScreenEffect", at = @At(value = "TAIL"))
    private static void es_renderScreenEffect(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
        if (minecraft.player != null && ESBlockUtil.isEntityInBlock(minecraft.player, ESBlocks.ABYSSAL_FIRE.get()) && !minecraft.player.isSpectator()) {
            renderAbyssalFlame(poseStack);
        }
    }

    @Unique
    private static void renderAbyssalFlame(PoseStack poseStack) {
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        TextureAtlasSprite textureAtlasSprite = ABYSSAL_FIRE_1.sprite();
        RenderSystem.setShaderTexture(0, textureAtlasSprite.atlasLocation());
        float f = textureAtlasSprite.getU0();
        float g = textureAtlasSprite.getU1();
        float h = (f + g) / 2.0F;
        float i = textureAtlasSprite.getV0();
        float j = textureAtlasSprite.getV1();
        float k = (i + j) / 2.0F;
        float l = textureAtlasSprite.uvShrinkRatio();
        float m = Mth.lerp(l, f, h);
        float n = Mth.lerp(l, g, h);
        float o = Mth.lerp(l, i, k);
        float p = Mth.lerp(l, j, k);

        for(int r = 0; r < 2; ++r) {
            poseStack.pushPose();
            poseStack.translate((float)(-(r * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees((float)(r * 2 - 1) * 10.0F));
            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
            bufferBuilder.vertex(matrix4f, -0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv(n, p).endVertex();
            bufferBuilder.vertex(matrix4f, 0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv(m, p).endVertex();
            bufferBuilder.vertex(matrix4f, 0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv(m, o).endVertex();
            bufferBuilder.vertex(matrix4f, -0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).uv(n, o).endVertex();
            BufferUploader.drawWithShader(bufferBuilder.end());
            poseStack.popPose();
        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
    }
}
