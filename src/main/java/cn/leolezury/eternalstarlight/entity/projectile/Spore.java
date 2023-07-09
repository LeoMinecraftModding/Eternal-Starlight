package cn.leolezury.eternalstarlight.entity.projectile;

import cn.leolezury.eternalstarlight.entity.attack.Vine;
import cn.leolezury.eternalstarlight.entity.misc.CameraShake;
import cn.leolezury.eternalstarlight.init.DamageTypeInit;
import cn.leolezury.eternalstarlight.init.EntityInit;
import cn.leolezury.eternalstarlight.init.ParticleInit;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
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

public class Spore extends ExplosiveProjectileEntity {
    public Spore(EntityType<? extends ExplosiveProjectileEntity> type, World world) {
        super(type, world);
    }

    public Spore(World world, LivingEntity livingEntity, double x, double y, double z) {
        super(EntityInit.SPORE, livingEntity, x, y, z, world);
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
    }

    protected ParticleEffect getParticleType() {
        return ParticleInit.POISON;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean damage(DamageSource damageSource, float amount) {
        return false;
    }

    protected boolean isBurning() {
        return false;
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, getSoundVolume(), getVoicePitch());
        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());

        if (getOwner() instanceof LivingEntity entity) {
            cloud.setOwner(entity);
        }
        cloud.setParticleType(ParticleInit.POISON);
        cloud.setRadius(1.5F);
        cloud.setDuration(200);
        cloud.addEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1));
        this.getWorld().spawnEntity(cloud);

        if (result.getSide().equals(Direction.UP)) {
            for (int i = 0; i < 5; i++) {
                Vine vine = EntityInit.VINE.create(getWorld());
                Random random = new Random();
                vine.setPos(getPos().add(random.nextDouble(), 0.2, random.nextDouble()));
                vine.setAttackMode(0);
                if (getOwner() instanceof LivingEntity entity) {
                    vine.setOwner(entity);
                }
                getWorld().spawnEntity(vine);
            }
        }
        if (!getWorld().isClient) {
            ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
        }

        CameraShake.cameraShake(getWorld(), getPos(), 45, 0.002f, 20, 10);
        discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        super.onEntityHit(result);

        if (getOwner() != null && !result.getEntity().getUuid().equals(getOwner().getUuid())) {
            result.getEntity().damage(DamageTypeInit.getIndirectEntityDamageSource(getWorld(), DamageTypeInit.POISON, this, getOwner()), 5);
        }

        playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, getSoundVolume(), getVoicePitch());
        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());

        if (getOwner() instanceof LivingEntity entity) {
            cloud.setOwner(entity);
        }
        cloud.setParticleType(ParticleInit.POISON);
        cloud.setRadius(1.5F);
        cloud.setDuration(200);
        cloud.addEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1));
        this.getWorld().spawnEntity(cloud);

        if (!getWorld().isClient) {
            ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
        }

        CameraShake.cameraShake(getWorld(), getPos(), 45, 0.002f, 20, 10);
        discard();
    }
}
