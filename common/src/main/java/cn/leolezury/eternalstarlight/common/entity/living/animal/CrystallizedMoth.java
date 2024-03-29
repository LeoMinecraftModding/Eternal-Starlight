package cn.leolezury.eternalstarlight.common.entity.living.animal;

import cn.leolezury.eternalstarlight.common.entity.living.goal.LookAtTargetGoal;
import cn.leolezury.eternalstarlight.common.network.ESParticlePacket;
import cn.leolezury.eternalstarlight.common.platform.ESPlatform;
import cn.leolezury.eternalstarlight.common.registry.ESParticles;
import cn.leolezury.eternalstarlight.common.util.ESMathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class CrystallizedMoth extends Animal implements FlyingAnimal {
    protected static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(CrystallizedMoth.class, EntityDataSerializers.INT);
    public int getAttackTicks() {
        return entityData.get(ATTACK_TICKS);
    }
    public void setAttackTicks(int attackTicks) {
        entityData.set(ATTACK_TICKS, attackTicks);
    }

    public CrystallizedMoth(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new MothMoveControl();
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setNoGravity(true);
    }

    public AnimationState idleAnimationState = new AnimationState();

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation pathNavigation = new FlyingPathNavigation(this, level);
        pathNavigation.setCanOpenDoors(false);
        pathNavigation.setCanFloat(true);
        pathNavigation.setCanPassDoors(true);
        return pathNavigation;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_TICKS, 0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new LookAtTargetGoal(this));
        goalSelector.addGoal(2, new CrystallizedMothAttackGoal());
        goalSelector.addGoal(3, new CrystallizedMothRandomMoveGoal());
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));

        targetSelector.addGoal(0, new HurtByTargetGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Bat.class, false));
    }

    private class MothMoveControl extends MoveControl {
        public MothMoveControl() {
            super(CrystallizedMoth.this);
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - mob.getX(), this.wantedY - mob.getY(), this.wantedZ - mob.getZ());
                double length = vec3.length();
                double size = mob.getBoundingBox().getSize();
                Vec3 delta = vec3.scale(this.speedModifier * 0.025D / length);
                mob.setDeltaMovement(mob.getDeltaMovement().add(delta));
                if (length < size * 0.8F) {
                    this.operation = Operation.WAIT;
                } else if (length >= size && CrystallizedMoth.this.getAttackTicks() <= 0) {
                    mob.setYRot(-((float) Mth.atan2(delta.x, delta.z)) * Mth.RAD_TO_DEG);
                }
            }
            mob.setNoGravity(true);
        }
    }

    private class CrystallizedMothRandomMoveGoal extends Goal {
        public CrystallizedMothRandomMoveGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }
        
        public boolean canUse() {
            return CrystallizedMoth.this.navigation.isDone() && CrystallizedMoth.this.random.nextInt(5) == 0;
        }
        
        public boolean canContinueToUse() {
            return CrystallizedMoth.this.navigation.isInProgress() && CrystallizedMoth.this.random.nextInt(30) != 0;
        }

        public void start() {
            Vec3 target = this.getRandomPos();
            if (target != null) {
                CrystallizedMoth.this.navigation.moveTo(target.x, target.y, target.z, 1d);
            }
        }

        @Nullable
        private Vec3 getRandomPos() {
            Vec3 vec3 = CrystallizedMoth.this.getViewVector(0.0F);
            Vec3 vec32 = HoverRandomPos.getPos(CrystallizedMoth.this, 8, 7, vec3.x, vec3.z, Mth.HALF_PI, 3, 1);
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(CrystallizedMoth.this, 8, 4, -2, vec3.x, vec3.z, Mth.HALF_PI);
        }
    }

    private class CrystallizedMothAttackGoal extends Goal {
        public CrystallizedMothAttackGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return CrystallizedMoth.this.getTarget() != null && CrystallizedMoth.this.getAttackTicks() == 0 && CrystallizedMoth.this.random.nextInt(100) == 0;
        }

        public boolean canContinueToUse() {
            return CrystallizedMoth.this.getAttackTicks() < 100;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            CrystallizedMoth.this.setAttackTicks(CrystallizedMoth.this.getAttackTicks() + 1);
            if (CrystallizedMoth.this.getTarget() != null) {
                LivingEntity target = CrystallizedMoth.this.getTarget();
                Vec3 selfPos = CrystallizedMoth.this.position().add(0, CrystallizedMoth.this.getBbHeight() / 2f, 0);
                Vec3 pos = target.position().add(0, target.getBbHeight() / 2f, 0);
                Vec3 subtract = pos.subtract(selfPos);
                CrystallizedMoth.this.setYRot(-((float) Mth.atan2(subtract.x, subtract.z)) * Mth.RAD_TO_DEG);
                if (CrystallizedMoth.this.hasLineOfSight(target) && distanceTo(target) < 30) {
                    CrystallizedMoth.this.doHurtTarget(target);
                }
                if (CrystallizedMoth.this.level() instanceof ServerLevel serverLevel && CrystallizedMoth.this.getAttackTicks() % 5 == 0) {
                    Vec3 delta = pos.subtract(selfPos).normalize().scale(0.8);
                    ESPlatform.INSTANCE.sendToAllClients(serverLevel, new ESParticlePacket(ESParticles.CRYSTALLIZED_MOTH_SONAR.get(), selfPos.x, selfPos.y, selfPos.z, delta.x, delta.y, delta.z));
                }
                Vec3 wanted = ESMathUtil.rotationToPosition(pos, 10, 0, ESMathUtil.positionToYaw(pos, selfPos) + 5);
                CrystallizedMoth.this.getMoveControl().setWantedPosition(wanted.x, wanted.y, wanted.z, 1);
            }
        }

        @Override
        public void stop() {
            CrystallizedMoth.this.setAttackTicks(0);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 1.5).add(Attributes.FOLLOW_RANGE, 50).add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.FLYING_SPEED, 0.6);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (level().isClientSide) {
            idleAnimationState.startIfStopped(tickCount);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    public static boolean checkMothSpawnRules(EntityType<? extends CrystallizedMoth> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.canSeeSky(pos) && pos.getY() < level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()) - 20;
    }
}
