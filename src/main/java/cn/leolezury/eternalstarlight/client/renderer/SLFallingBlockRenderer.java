package cn.leolezury.eternalstarlight.client.renderer;

import cn.leolezury.eternalstarlight.entity.misc.SLFallingBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Environment(EnvType.CLIENT)
public class SLFallingBlockRenderer extends EntityRenderer<SLFallingBlock> {
    private BlockRenderManager manager;

    public SLFallingBlockRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.manager = context.getBlockRenderManager();
    }

    public void render(SLFallingBlock block, float yaw, float tickDelta, MatrixStack stack, VertexConsumerProvider vertexConsumerProvider, int light) {
        BlockState blockstate = block.getBlockState();
        if (blockstate.getRenderType() == BlockRenderType.MODEL) {
            World world = block.level();
            if (blockstate != world.getBlockState(block.blockPosition()) && blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                stack.pushPose();
                BlockPos blockpos = BlockPos.containing(block.getX(), block.getBoundingBox().maxY, block.getZ());
                stack.translate(-0.5D, 0.0D, -0.5D);
                var model = this.dispatcher.getBlockModel(blockstate);
                for (var renderType : model.getRenderTypes(blockstate, RandomSource.create(blockstate.getSeed(block.getStartPos())), net.minecraftforge.client.model.data.ModelData.EMPTY))
                    this.dispatcher.getModelRenderer().tesselateBlock(world, model, blockstate, blockpos, stack, vertexConsumerProvider.getBuffer(renderType), false, RandomSource.create(), blockstate.getSeed(block.getStartPos()), OverlayTexture.NO_OVERLAY, net.minecraftforge.client.model.data.ModelData.EMPTY, renderType);
                stack.popPose();
                super.render(block, yaw, tickDelta, stack, vertexConsumerProvider, light);
            }
        }
    }

    public ResourceLocation getTextureLocation(SLFallingBlock p_114632_) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
