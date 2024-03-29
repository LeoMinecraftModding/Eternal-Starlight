package cn.leolezury.eternalstarlight.common.entity.projectile;

import cn.leolezury.eternalstarlight.common.data.ESDamageTypes;
import cn.leolezury.eternalstarlight.common.entity.attack.LunarVine;
import cn.leolezury.eternalstarlight.common.entity.misc.CameraShake;
import cn.leolezury.eternalstarlight.common.registry.ESEntities;
import cn.leolezury.eternalstarlight.common.registry.ESParticles;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.Random;

public class LunarSpore extends AbstractHurtingProjectile {
    public LunarSpore(EntityType<? extends AbstractHurtingProjectile> type, Level level) {
        super(type, level);
    }

    public LunarSpore(Level level, LivingEntity entity, double x, double y, double z) {
        super(ESEntities.LUNAR_SPORE.get(), entity, x, y, z, level);
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
    }

    protected ParticleOptions getTrailParticle() {
        return ESParticles.POISON.get();
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean hurt(DamageSource damageSource, float amount) {
        return false;
    }

    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        playSound(SoundEvents.GENERIC_EXPLODE, getSoundVolume(), getVoicePitch());
        AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());

        if (getOwner() instanceof LivingEntity entity) {
            cloud.setOwner(entity);
        }
        cloud.setParticle(ESParticles.POISON.get());
        cloud.setRadius(1.5F);
        cloud.setDuration(200);
        cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
        this.level().addFreshEntity(cloud);

        if (result.getDirection().equals(Direction.UP)) {
            for (int i = 0; i < 5; i++) {
                LunarVine vine = ESEntities.LUNAR_VINE.get().create(level());
                Random random = new Random();
                vine.setPos(position().add(random.nextDouble(), 0.2, random.nextDouble()));
                vine.setAttackMode(0);
                if (getOwner() instanceof LivingEntity entity) {
                    vine.setOwner(entity);
                }
                level().addFreshEntity(vine);
            }
        }
        if (!level().isClientSide) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
        }

        CameraShake.createCameraShake(level(), position(), 45, 0.002f, 20, 10);
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (getOwner() != null && !result.getEntity().getUUID().equals(getOwner().getUUID())) {
            result.getEntity().hurt(ESDamageTypes.getIndirectEntityDamageSource(level(), ESDamageTypes.POISON, this, getOwner()), 5);
        }

        playSound(SoundEvents.GENERIC_EXPLODE, getSoundVolume(), getVoicePitch());
        AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());

        if (getOwner() instanceof LivingEntity entity) {
            cloud.setOwner(entity);
        }
        cloud.setParticle(ESParticles.POISON.get());
        cloud.setRadius(1.5F);
        cloud.setDuration(200);
        cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
        this.level().addFreshEntity(cloud);

        if (!level().isClientSide) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
        }

        CameraShake.createCameraShake(level(), position(), 45, 0.002f, 20, 10);
        discard();
    }
}
