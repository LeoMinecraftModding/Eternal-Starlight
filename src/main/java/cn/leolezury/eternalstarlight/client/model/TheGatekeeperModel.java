package cn.leolezury.eternalstarlight.client.model;

import cn.leolezury.eternalstarlight.EternalStarlight;
import cn.leolezury.eternalstarlight.client.model.animation.GatekeeperKeyframeAnimations;
import cn.leolezury.eternalstarlight.client.model.animation.TheGatekeeperAnimation;
import cn.leolezury.eternalstarlight.entity.boss.TheGatekeeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class TheGatekeeperModel<T extends TheGatekeeper> extends HumanoidModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EternalStarlight.MOD_ID, "the_gatekeeper"), "main");
    public static final ModelLayerLocation SLIM_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EternalStarlight.MOD_ID, "the_gatekeeper_slim"), "main");
    public static final ModelLayerLocation INNER_ARMOR_LOCATION = new ModelLayerLocation(new ResourceLocation(EternalStarlight.MOD_ID, "the_gatekeeper"), "inner_armor");
    public static final ModelLayerLocation OUTER_ARMOR_LOCATION = new ModelLayerLocation(new ResourceLocation(EternalStarlight.MOD_ID, "the_gatekeeper"), "outer_armor");

    private final ModelPart root;
    private final boolean slim;
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
    
    public TheGatekeeperModel(ModelPart root, boolean slim) {
        super(root);
        this.root = root;
        this.slim = slim;
    }

    public static LayerDefinition createBodyLayer(boolean slim) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        if (slim) {
            PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.75F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 32).addBox(-2.0F, -1.75F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

            PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -1.75F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(48, 48).addBox(-1.0F, -1.75F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));
        } else {
            PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

            PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                    .texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));
        }

        //useless model parts to replace vanilla model parts
        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public Optional<ModelPart> getAnyDescendantWithName(String name) {
        return name.equals("root") ? Optional.of(root) : root.getAllParts().filter((part) -> part.hasChild(name)).findFirst().map((part) -> part.getChild(name));
    }

    protected void animate(AnimationState state, AnimationDefinition definition, float speed) {
        this.animate(state, definition, speed, 1.0F);
    }

    protected void animate(AnimationState state, AnimationDefinition definition, float speed, float f) {
        state.updateTime(speed, f);
        state.ifStarted((animState) -> {
            GatekeeperKeyframeAnimations.animate(this, definition, animState.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
        });
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        root.getAllParts().forEach(ModelPart::resetPose);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (entity.getAttackTicks() >= 0 && entity.getAttackState() != 0 && entity.deathTime <= 0) {
            int state = entity.getAttackState();
            switch (state) {
                case -1 -> {
                    animate(entity.switchPhaseAnimationState, TheGatekeeperAnimation.SWITCH_PHASE, ageInTicks);
                }
                case 1 -> {
                    animate(entity.meleeAttackAnimationState, TheGatekeeperAnimation.MELEE, ageInTicks);
                }
                case 2 -> {
                    animate(entity.jumpAnimationState, TheGatekeeperAnimation.JUMP, ageInTicks);
                }
                case 3 -> {
                    animate(entity.inAirAnimationState, TheGatekeeperAnimation.IN_AIR, ageInTicks);
                }
                case 4 -> {
                    animate(entity.landAnimationState, TheGatekeeperAnimation.LAND, ageInTicks);
                }
            }
        }
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
        this.leftArmPose = ArmPose.EMPTY;
        this.rightArmPose = ArmPose.EMPTY;
        if (entity.getAttackState() == 0) {
            if (entity.isGatekeeperBlocking()) {
                this.leftArmPose = ArmPose.BLOCK;
            } else if (entity.isUsingItem()) {
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            }
        }
    }

    public void renderHeadToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.headParts().forEach((modelPart) -> modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha));
    }


    public void translateToHand(HumanoidArm arm, PoseStack stack) {
        ModelPart modelpart = this.getArm(arm);
        if (this.slim) {
            float f = 0.5F * (float)(arm == HumanoidArm.RIGHT ? 1 : -1);
            modelpart.x += f;
            modelpart.translateAndRotate(stack);
            modelpart.x -= f;
        } else {
            modelpart.translateAndRotate(stack);
        }
    }
}
