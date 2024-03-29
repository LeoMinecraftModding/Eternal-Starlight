package cn.leolezury.eternalstarlight.common.entity.living.monster;

import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;

public class NightshadeSpider extends Spider {
    public NightshadeSpider(EntityType<? extends NightshadeSpider> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createNightshadeSpider() {
        return Spider.createAttributes().add(Attributes.MAX_HEALTH, 10.0D);
    }

    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity) {
                int effectTime = 0;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    effectTime = 7;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    effectTime = 15;
                }
                if (effectTime > 0) {
                    ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.GLOWING, effectTime * 20, 0), this);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 0.45F;
    }
}
