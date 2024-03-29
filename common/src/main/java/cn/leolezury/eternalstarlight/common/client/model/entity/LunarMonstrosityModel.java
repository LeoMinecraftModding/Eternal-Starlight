package cn.leolezury.eternalstarlight.common.client.model.entity;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.client.model.ModelUtils;
import cn.leolezury.eternalstarlight.common.client.model.animation.AnimatedEntityModel;
import cn.leolezury.eternalstarlight.common.client.model.animation.definition.LunarMonstrosityAnimation;
import cn.leolezury.eternalstarlight.common.entity.living.boss.LunarMonstrosity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@Environment(EnvType.CLIENT)
public class LunarMonstrosityModel<T extends LunarMonstrosity> extends AnimatedEntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EternalStarlight.MOD_ID, "moonflower"), "main");
    private final ModelPart root;
    private final ModelPart lowerBody;
    private final ModelPart upperBody;
    private final ModelPart head;

    public LunarMonstrosityModel(ModelPart root) {
        this.root = root;
        lowerBody = root.getChild("lower_body");
        upperBody = root.getChild("lower_body").getChild("upper_body");
        head = root.getChild("lower_body").getChild("upper_body").getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        base.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -2.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

        base.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 11).addBox(-14.0F, -1.0F, -2.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

        base.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, -1.0F, -6.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.4363F, 0.0F));

        base.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 33).addBox(-14.0F, -1.0F, -6.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -0.4363F, 0.0F));

        PartDefinition lowerBody = partdefinition.addOrReplaceChild("lower_body", CubeListBuilder.create().texOffs(0, 44).addBox(-3.0F, -24.0F, -2.0F, 6.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition upperBody = lowerBody.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(20, 44).addBox(-3.0F, -22.5F, -2.0F, 6.0F, 23.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.5F, 0.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition head = upperBody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 3).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.5F, -3.0F, -0.2618F, 0.0F, 0.0F));

        head.addOrReplaceChild("petal1", CubeListBuilder.create().texOffs(47, 21).addBox(-13.0F, -5.0F, -0.5F, 14.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, -3.5F, 0.0F, -0.4363F, 0.0F));

        head.addOrReplaceChild("petal2", CubeListBuilder.create().texOffs(40, 44).addBox(-1.0F, -5.0F, -0.5F, 14.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, -3.5F, 0.0F, 0.4363F, 0.0F));

        head.addOrReplaceChild("petal3", CubeListBuilder.create().texOffs(62, 65).addBox(-5.0F, -12.5F, -0.5F, 10.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, -3.5F, 0.4363F, 0.0F, 0.0F));

        head.addOrReplaceChild("petal4", CubeListBuilder.create().texOffs(40, 65).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, -3.5F, -0.4363F, 0.0F, 0.0F));

        head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(40, 55).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.0F));

        head.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(38, 33).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -4.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        head.xRot = headPitch * ((float)Math.PI / 180F);
        if (entity.getAttackTicks() >= 0 && entity.getAttackState() != 0 && entity.deathTime <= 0) {
            int state = entity.getAttackState();
            switch (state) {
                case -2 -> {
                    entity.headPos = ModelUtils.getModelPosition(entity, netHeadYaw, List.of(head, upperBody, lowerBody));
                }
                case -1 -> {
                    animate(entity.switchPhaseAnimationState, LunarMonstrosityAnimation.SWITCH_PHASE, ageInTicks);
                }
                case 1 -> {
                    animate(entity.toxicBreathAnimationState, LunarMonstrosityAnimation.TOXIC_BREATH, ageInTicks);
                    entity.headPos = ModelUtils.getModelPosition(entity, netHeadYaw, List.of(head, upperBody, lowerBody));
                }
                case 2 -> {
                    animate(entity.sporeAnimationState, LunarMonstrosityAnimation.SPORE, ageInTicks);
                }
                case 3 -> {
                    animate(entity.vineAnimationState, LunarMonstrosityAnimation.VINE, ageInTicks);
                }
                case 4 -> {
                    animate(entity.biteAnimationState, LunarMonstrosityAnimation.BITE, ageInTicks);
                }
                case 5 -> {
                    animate(entity.disappearAnimationState, LunarMonstrosityAnimation.DISAPPEAR, ageInTicks);
                }
                case 6 -> {
                    animate(entity.sneakAnimationState, LunarMonstrosityAnimation.SNEAK, ageInTicks);
                }
                case 7 -> {
                    animate(entity.appearAnimationState, LunarMonstrosityAnimation.APPEAR, ageInTicks);
                }
            }
        }
        if (entity.deathTime > 0) {
            animate(entity.deathAnimationState, LunarMonstrosityAnimation.DEATH, ageInTicks);
        }
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
