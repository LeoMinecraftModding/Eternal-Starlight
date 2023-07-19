package cn.leolezury.eternalstarlight.entity.misc;

import cn.leolezury.eternalstarlight.client.particle.lightning.LightningParticleOptions;
import cn.leolezury.eternalstarlight.datagen.generator.DamageTypeGenerator;
import cn.leolezury.eternalstarlight.init.EntityInit;
import cn.leolezury.eternalstarlight.init.ParticleInit;
import cn.leolezury.eternalstarlight.util.MathUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class AetherSentMeteor extends AbstractHurtingProjectile {
    protected static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(AetherSentMeteor.class, EntityDataSerializers.INT);
    public int getSize() {
        return entityData.get(SIZE);
    }
    public void setSize(int size) {
        entityData.set(SIZE, size);
    }
    protected static final EntityDataAccessor<Integer> TICKS_SINCE_LANDED = SynchedEntityData.defineId(AetherSentMeteor.class, EntityDataSerializers.INT);
    public int getTicksSinceLanded() {
        return entityData.get(TICKS_SINCE_LANDED);
    }
    public void setTicksSinceLanded(int ticksSinceLanded) {
        entityData.set(TICKS_SINCE_LANDED, ticksSinceLanded);
    }

    public AetherSentMeteor(EntityType<? extends AbstractHurtingProjectile> type, Level level) {
        super(type, level);
    }

    public AetherSentMeteor(Level level, LivingEntity entity, double x, double y, double z) {
        super(EntityInit.AETHERSENT_METEOR.get(), entity, x, y, z, level);
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(SIZE, 0);
        entityData.define(TICKS_SINCE_LANDED, 0);
        super.defineSynchedData();
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        setSize(compoundTag.getInt("Size"));
        setTicksSinceLanded(compoundTag.getInt("TicksSinceLanded"));
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("Size", getSize());
        compoundTag.putInt("TicksSinceLanded", getTicksSinceLanded());
    }

    private void doHurtEntity(float damageScale) {
        for (LivingEntity livingEntity : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(getSize()))) {
            if (!(getOwner() instanceof Player) || livingEntity instanceof Enemy) {
                livingEntity.hurt(DamageTypeGenerator.getEntityDamageSource(level(), DamageTypeGenerator.METEOR, getOwner()), getSize() * damageScale);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        doHurtEntity(5);
        super.onHit(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        setTicksSinceLanded(1);
        playSound(SoundEvents.GENERIC_EXPLODE, getSoundVolume(), getVoicePitch());
        super.onHitBlock(result);
    }

    @Override
    public void tick() {
        super.tick();
        if (getSize() <= 0) {
            discard();
        }

        refreshDimensions();
        if (getTicksSinceLanded() > 0) {
            setDeltaMovement(0, 0, 0);
            if (getTicksSinceLanded() % 5 == 0) {
                CameraShake.cameraShake(level(), position(), getSize() * 20, 0.0005f * getSize(), 5, 5);
            }
            setTicksSinceLanded(getTicksSinceLanded() + 1);
            if (getTicksSinceLanded() > getSize() * 10) {
                discard();
            }
            if (level().isClientSide) {
                level().addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY() + 0.05 * getSize(), getZ(), 0, 0, 0);
                for (int i = 0; i < 5; i++) {
                    float pitch = random.nextInt(361);
                    float yaw = random.nextInt(361);
                    float len = random.nextInt(getSize());
                    Vec3 particleTarget = MathUtil.rotationToPosition(position(), getSize() / 2f, pitch, yaw);
                    Vec3 particleStart = MathUtil.rotationToPosition(position(), len, pitch, yaw);
                    Vec3 motion = particleTarget.subtract(particleStart);
                    level().addParticle(new LightningParticleOptions(new Vector3f(0.7f, 0.07f, 0.78f)), particleStart.x, particleStart.y, particleStart.z, motion.x, motion.y, motion.z);
                }
            } else if (getTicksSinceLanded() % 5 == 0) {
                doHurtEntity(0.5f);
            }
        } else {
            setDeltaMovement(0, -1, 0);
            if (level().isClientSide) {
                Vec3 motion = getDeltaMovement();
                for (int i = 0; i < 2; i++) {
                    level().addParticle(new LightningParticleOptions(new Vector3f(0.7f, 0.07f, 0.78f)), getX(), getY(), getZ(), -motion.x * 3, -motion.y * 3, -motion.z * 3);
                }
            }
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(getSize() / 10f);
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
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
}