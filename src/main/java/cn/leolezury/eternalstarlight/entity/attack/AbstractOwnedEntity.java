package cn.leolezury.eternalstarlight.entity.attack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractOwnedEntity extends Entity {
    World world;
    public AbstractOwnedEntity(EntityType<?> type, World world) {
        super(type, world);
    }
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerId;
    public LivingEntity getOwner() {
        return owner;
    }
    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }
    @Nullable
    private LivingEntity target;
    @Nullable
    private UUID targetId;
    public LivingEntity getGoal() {
        return target;
    }
    public void setGoal(LivingEntity target) {
        this.target = target;
    }
    protected static final TrackedData<Integer> SPAWNED_TICKS = DataTracker.registerData(AbstractOwnedEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public int getSpawnedTicks() {
        return dataTracker.get(SPAWNED_TICKS);
    }
    public void setSpawnedTicks(int spawnedTicks) {
        dataTracker.set(SPAWNED_TICKS, spawnedTicks);
    }
    protected static final TrackedData<Integer> ATTACK_MODE = DataTracker.registerData(AbstractOwnedEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public int getAttackMode() {
        return dataTracker.get(ATTACK_MODE);
    }
    public void setAttackMode(int attackMode) {
        dataTracker.set(ATTACK_MODE, attackMode);
    }

    protected void readAdditionalSaveData(NbtCompound nbtCompound) {
        if (nbtCompound.containsUuid("Owner")) {
            ownerId = nbtCompound.getUuid("Owner");
        }
        if (nbtCompound.containsUuid("Target")) {
            targetId = nbtCompound.getUuid("Target");
        }
        setSpawnedTicks(nbtCompound.getInt("SpawnedTicks"));
        setAttackMode(nbtCompound.getInt("AttackMode"));
    }

    protected void addAdditionalSaveData(NbtCompound nbtCompound) {
        if (owner != null) {
            nbtCompound.putUuid("Owner", owner.getUuid());
        }
        if (target != null) {
            nbtCompound.putUuid("Target", target.getUuid());
        }
        nbtCompound.putInt("SpawnedTicks", getSpawnedTicks());
        nbtCompound.putInt("AttackMode", getAttackMode());
    }

    protected void defineSynchedData() {
        dataTracker.startTracking(SPAWNED_TICKS, 0);
        dataTracker.startTracking(ATTACK_MODE, 0);
    }

    public boolean shouldContinueToTick() {
        return false;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
    }

    @Override
    public boolean damage(DamageSource damageSource, float amount) {
        if (damageSource.equals(getDamageSources().outOfWorld())) {
            discard();
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!world.isClient) {
            if (owner == null && ownerId != null) {
                if (((ServerWorld)this.world).getEntity(ownerId) instanceof LivingEntity livingEntity) {
                    owner = livingEntity;
                }
                if (owner == null) {
                    ownerId = null;
                }
            }
            if (target == null && targetId != null) {
                if (((ServerWorld)this.world).getEntity(targetId) instanceof LivingEntity livingEntity) {
                    target = livingEntity;
                }
                if (target == null) {
                    targetId = null;
                }
            }
            if (shouldContinueToTick()) {
                setSpawnedTicks(getSpawnedTicks() + 1);
            }
            this.move(MovementType.SELF, this.getVelocity());
            if (!this.onGround/*onGround()*/ && !hasNoGravity()) {
                setVelocity(getVelocity().add(0, hasNoGravity() ? 0 : -0.2, 0));
            }
            setVelocity(getVelocity().multiply/*.scale*/(0.8));
        }
    }
}
