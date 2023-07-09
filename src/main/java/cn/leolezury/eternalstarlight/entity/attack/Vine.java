package cn.leolezury.eternalstarlight.entity.attack;

import cn.leolezury.eternalstarlight.entity.misc.CameraShake;
import cn.leolezury.eternalstarlight.init.DamageTypeInit;
import cn.leolezury.eternalstarlight.init.ParticleInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.Random;

public class Vine extends AbstractOwnedEntity {
    public Vine(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public boolean shouldContinueToTick() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!world.isClient) {
            if (getSpawnedTicks() >= 200) {
                this.discard();
            }
            if (getSpawnedTicks() == 10) {
                CameraShake.cameraShake(world, getPos(), 30, 0.001f, 20, 10);
            }
            if (getSpawnedTicks() > 40 && getOwner() != null) {
                if (getAttackMode() == 0) {
                    for (LivingEntity livingEntity : world.getNonSpectatingEntities(LivingEntity.class, getBoundingBox().expand(0.5))) {
                        if (!livingEntity.getUuid().equals(getOwner().getUuid())) {
                            livingEntity.damage(DamageTypeInit.getIndirectEntityDamageSource(world, DamageTypeInit.POISON, this, getOwner()), 4);
                        }
                    }
                }
                if (getAttackMode() == 1) {
                    for (LivingEntity livingEntity : world.getNonSpectatingEntities(LivingEntity.class, getBoundingBox().expand(0.5))) {
                        if (!livingEntity.getUuid().equals(getOwner().getUuid())) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 4));
                            livingEntity.damage(DamageTypeInit.getIndirectEntityDamageSource(world, DamageTypeInit.POISON, this, getOwner()), 1);
                        }
                    }
                }
            }
        } else {
            Random random = new Random();
            world.addParticle(ParticleInit.POISON, getX() + (random.nextDouble() - 0.5) * 1, getY() + 0.25 + (random.nextDouble() - 0.5) * 1, getZ() + (random.nextDouble() - 0.5) * 1, 0, 0, 0);
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
