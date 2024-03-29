package cn.leolezury.eternalstarlight.common.client.renderer.entity;

import cn.leolezury.eternalstarlight.common.client.renderer.ESRenderType;
import cn.leolezury.eternalstarlight.common.entity.attack.ray.RayAttack;
import cn.leolezury.eternalstarlight.common.util.ESMathUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

@Environment(EnvType.CLIENT)
public abstract class LaserBeamRenderer<T extends RayAttack> extends EntityRenderer<T> {
    public LaserBeamRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    
    public float getTextureWidth() {
        return 32;
    }

    public float getBeamRadius() {
        return 1;
    }

    @Override
    public void render(T laserBeam, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferSource, int packedLight) {
        Vec3 endPos = ESMathUtil.rotationToPosition(laserBeam.position(), laserBeam.getLength(), Mth.rotLerp(partialTicks, laserBeam.prevPitch, laserBeam.getPitch()), Mth.rotLerp(partialTicks, laserBeam.prevYaw, laserBeam.getYaw()));
        double targetX = endPos.x;
        double targetY = endPos.y;
        double targetZ = endPos.z;

        double posX = Mth.lerp(partialTicks, laserBeam.xo, laserBeam.getX());
        double posY = Mth.lerp(partialTicks, laserBeam.yo, laserBeam.getY());
        double posZ = Mth.lerp(partialTicks, laserBeam.zo, laserBeam.getZ());

        float yaw = Mth.lerp(partialTicks, laserBeam.prevYaw, laserBeam.getYaw()) * Mth.DEG_TO_RAD;
        float pitch = Mth.lerp(partialTicks, laserBeam.prevPitch, laserBeam.getPitch()) * Mth.DEG_TO_RAD;

        float length = (float) Math.sqrt(Math.pow(targetX - posX, 2) + Math.pow(targetY - posY, 2) + Math.pow(targetZ - posZ, 2));

        VertexConsumer consumer = bufferSource.getBuffer(ESRenderType.glow(getTextureLocation(laserBeam)));
        // consumer = bufferSource.getBuffer(ESRenderType.laserBeam(getTextureLocation(laserBeam)));

        // render beam
        renderBeam(length, 180f / (float) Math.PI * yaw, 180f / (float) Math.PI * pitch, laserBeam.tickCount, stack, consumer, packedLight);
    }

    private void renderBeamPart(float length, int tickCount, PoseStack stack, VertexConsumer consumer, int packedLight) {
        PoseStack.Pose pose = stack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();
        float factor = (float) Math.sin(tickCount + Minecraft.getInstance().getFrameTime());
        float xOffset = ((tickCount + Minecraft.getInstance().getFrameTime()) * 0.2f) % getTextureWidth();
        vertex(matrix4f, matrix3f, consumer, -getBeamRadius() * 0.8f - factor * getBeamRadius() * 0.2f, 0, 0, -xOffset, 0, 1, packedLight);
        vertex(matrix4f, matrix3f, consumer, -getBeamRadius() * 0.8f - factor * getBeamRadius() * 0.2f, length, 0, 1 - xOffset, 0, 1, packedLight);
        vertex(matrix4f, matrix3f, consumer, getBeamRadius() * 0.8f + factor * getBeamRadius() * 0.2f, length, 0, 1 - xOffset, 1, 1, packedLight);
        vertex(matrix4f, matrix3f, consumer, getBeamRadius() * 0.8f + factor * getBeamRadius() * 0.2f, 0, 0, -xOffset, 1, 1, packedLight);
    }

    private void renderBeam(float length, float yaw, float pitch, int tickCount, PoseStack matrixStackIn, VertexConsumer builder, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose((new Quaternionf()).rotationX(90.0F * (float) Math.PI / 180f));
        matrixStackIn.mulPose((new Quaternionf()).rotationZ((yaw - 90.0F) * (float) Math.PI / 180f));
        matrixStackIn.mulPose((new Quaternionf()).rotationX(-pitch * (float) Math.PI / 180f));

        float angle = (tickCount + Minecraft.getInstance().getFrameTime());

        matrixStackIn.pushPose();
        matrixStackIn.mulPose((new Quaternionf()).rotationY((angle) * (float) Math.PI / 180f));

        renderBeamPart(length, tickCount, matrixStackIn, builder, packedLightIn);
        matrixStackIn.popPose();

        for (int i = 1; i < 3; i++) {
            matrixStackIn.pushPose();
            matrixStackIn.mulPose((new Quaternionf()).rotationY((i * 30) * (float) Math.PI / 180f));

            renderBeamPart(length, tickCount, matrixStackIn, builder, packedLightIn);
            matrixStackIn.popPose();
        }

        matrixStackIn.popPose();
    }

    public void vertex(Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer consumer, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, float alpha, int packedLight) {
        consumer.vertex(matrix4f, offsetX, offsetY, offsetZ).color(1, 1, 1, 1 * alpha).uv(textureX, textureY).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }
}
