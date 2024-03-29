package cn.leolezury.eternalstarlight.common.entity.living.npc.boarwarf;

import cn.leolezury.eternalstarlight.common.data.ESRegistries;
import cn.leolezury.eternalstarlight.common.entity.living.goal.*;
import cn.leolezury.eternalstarlight.common.entity.living.npc.boarwarf.golem.AstralGolem;
import cn.leolezury.eternalstarlight.common.registry.ESBoarwarfProfessions;
import cn.leolezury.eternalstarlight.common.registry.ESSoundEvents;
import cn.leolezury.eternalstarlight.common.util.ESEntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.apache.commons.compress.utils.Sets;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class Boarwarf extends PathfinderMob implements Npc, Merchant {
    public Boarwarf(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Nullable
    private Player customer;
    @Nullable
    protected MerchantOffers offers;
    private int restockCoolDown = 0;
    private int chatCoolDown = 0;
    public int chatTicks = 0;
    private int awakeTicks = 0;
    private int sleepTicks = 0;
    public BlockPos homePos = BlockPos.ZERO;
    public Boarwarf chatTarget = null;
    public final AnimationState idleAnimationState = new AnimationState();

    protected static final EntityDataAccessor<String> TYPE = SynchedEntityData.defineId(Boarwarf.class, EntityDataSerializers.STRING);
    public ResourceLocation getTypeId() {
        return new ResourceLocation(entityData.get(TYPE));
    }
    public BoarwarfType getBoarwarfType() {
        return level().registryAccess().registryOrThrow(ESRegistries.BOARWARF_TYPE).get(getTypeId());
    }
    public void setTypeId(ResourceLocation typeId) {
        entityData.set(TYPE, typeId.toString());
    }
    public void setBoarwarfType(BoarwarfType type) {
        ResourceLocation key = level().registryAccess().registryOrThrow(ESRegistries.BOARWARF_TYPE).getKey(type);
        if (key != null) {
            setTypeId(key);
        }
    }

    protected static final EntityDataAccessor<String> PROFESSION = SynchedEntityData.defineId(Boarwarf.class, EntityDataSerializers.STRING);
    public ResourceLocation getProfessionId() {
        return new ResourceLocation(entityData.get(PROFESSION));
    }
    public AbstractBoarwarfProfession getProfession() {
        return ESBoarwarfProfessions.PROFESSIONS.registry().get(getProfessionId());
    }
    public void setProfessionId(ResourceLocation professionId) {
        entityData.set(PROFESSION, professionId.toString());
    }
    public void setProfession(AbstractBoarwarfProfession profession) {
        ResourceLocation key = ESBoarwarfProfessions.PROFESSIONS.registry().getKey(profession);
        if (key != null) {
            setProfessionId(key);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(TYPE, "null");
        entityData.define(PROFESSION, "null");
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        setTypeId(new ResourceLocation(compoundTag.getString("Type")));
        setProfessionId(new ResourceLocation(compoundTag.getString("Profession")));
        restockCoolDown = compoundTag.getInt("RestockCoolDown");
        chatCoolDown = compoundTag.getInt("ChatCoolDown");
        chatTicks = compoundTag.getInt("ChatTicks");
        awakeTicks = compoundTag.getInt("AwakeTicks");
        sleepTicks = compoundTag.getInt("SleepTicks");
        homePos = new BlockPos(compoundTag.getInt("HomeX"), compoundTag.getInt("HomeY"), compoundTag.getInt("HomeZ"));
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.addTrades();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putString("Type", getTypeId().toString());
        compoundTag.putString("Profession", getProfessionId().toString());
        compoundTag.putInt("RestockCoolDown", restockCoolDown);
        compoundTag.putInt("ChatCoolDown", chatCoolDown);
        compoundTag.putInt("ChatTicks", chatTicks);
        compoundTag.putInt("AwakeTicks", awakeTicks);
        compoundTag.putInt("SleepTicks", sleepTicks);
        compoundTag.putInt("HomeX", homePos.getX());
        compoundTag.putInt("HomeY", homePos.getY());
        compoundTag.putInt("HomeZ", homePos.getZ());
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new BoarwarfDoSleepGoal(this));
        goalSelector.addGoal(0, new BoarwarfWakeUpGoal(this));
        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new BoarwarfAvoidGoal(this));
        goalSelector.addGoal(3, new BoarwarfTradeWithPlayerGoal(this));
        goalSelector.addGoal(3, new BoarwarfLookAtCustomerGoal(this));
        goalSelector.addGoal(4, new BoarwarfMoveToBedGoal(this, 0.5D, 10));
        goalSelector.addGoal(5, new BoarwarfChatGoal(this, 0.3D, true));
        goalSelector.addGoal(6, new BoarwarfRandomStrollNearHomeGoal(this));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, LivingEntity.class, 32.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        homePos = blockPosition();

        level().registryAccess().registryOrThrow(ESRegistries.BOARWARF_TYPE).forEach((type) -> {
            if (type.biome().value() == level.getBiome(blockPosition()).value()) {
                setBoarwarfType(type);
            }
        });

        return super.finalizeSpawn(level, instance, spawnType, data, tag);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            return hasCustomer() ? ESSoundEvents.BOARWARF_TRADE.get() : ESSoundEvents.BOARWARF_AMBIENT.get();
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ESSoundEvents.BOARWARF_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ESSoundEvents.BOARWARF_DEATH.get();
    }

    protected SoundEvent getYesOrNoSound(boolean yesSound) {
        return yesSound ? ESSoundEvents.BOARWARF_YES.get() : ESSoundEvents.BOARWARF_NO.get();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand playerHand) {
        ItemStack itemstack = player.getItemInHand(playerHand);
        if (/*TODO: itemstack.getItem() != SPAWN_EGG.get() && */this.isAlive() && !this.hasCustomer()) {
            if (!this.getOffers().isEmpty() && !this.level().isClientSide && getBoarwarfCredit(player) >= -30) {
                int credit = getBoarwarfCredit(player);
                if (credit > 0) {
                    credit /= 10;
                }
                for (MerchantOffer merchantoffer : this.getOffers()) {
                    merchantoffer.setSpecialPriceDiff(-Mth.floor((float)credit / 2));
                }
                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, playerHand);
        }
    }

    public boolean wantsToSleep() {
        return awakeTicks > 12000;
    }

    public boolean wantsToWake() {
        return sleepTicks > 12000;
    }

    public void angerNearbyAstralGolems(LivingEntity target, boolean replaceCurrentTarget) {
        for (AstralGolem golem : level().getEntitiesOfClass(AstralGolem.class, getBoundingBox().inflate(30))) {
            if ((replaceCurrentTarget || golem.getTarget() == null) && !(target instanceof Player player && player.getAbilities().instabuild)) {
                golem.setTarget(target);
            }
        }
    }

    public LivingEntity getEntityToAvoid(float maxDist) {
        LivingEntity toAvoid = null;
        float currentDist = maxDist;
        List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(maxDist));
        for (LivingEntity entity : entities) {
            if (entity instanceof Targeting targeting && targeting.getTarget() != null && targeting.getTarget().getUUID().equals(getUUID())) {
                angerNearbyAstralGolems(entity, false);
                if (currentDist > distanceTo(entity)) {
                    toAvoid = entity;
                    currentDist = distanceTo(entity);
                }
            }
            if (entity instanceof Player player && getBoarwarfCredit(player) <= -30) {
                angerNearbyAstralGolems(player, false);
                if (currentDist > distanceTo(entity)) {
                    toAvoid = entity;
                    currentDist = distanceTo(entity);
                }
            }
        }
        return toAvoid;
    }

    public static int getBoarwarfCredit(Player player) {
        return ESEntityUtil.getPersistentData(player).getInt("boarwarf_credit");
    }

    public static void setBoarwarfCredit(Player player, int credit) {
        ESEntityUtil.getPersistentData(player).putInt("boarwarf_credit", credit);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (chatCoolDown < 12000) {
            chatCoolDown += 12000;
        }
        if (chatTicks > 0) {
            chatTicks = 0;
        }
        if (source.getEntity() instanceof LivingEntity livingEntity) {
            angerNearbyAstralGolems(livingEntity, true);
        }
        if (source.getEntity() instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                int credit = (int) (getBoarwarfCredit(player) - amount);
                if (credit > -10000) {
                    setBoarwarfCredit(player, credit);
                }
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public void die(DamageSource source) {
        if (source.getEntity() instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                int credit = getBoarwarfCredit(player) - 20;
                if (credit > -10000) {
                    setBoarwarfCredit(player, credit);
                }
            }
        }
        super.die(source);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if (level().getGameTime() % 40 == 0) {
                this.heal(1);
            }
            if (isSleeping()) {
                sleepTicks++;
                awakeTicks = 0;
            } else {
                awakeTicks++;
                sleepTicks = 0;
            }
            if (restockCoolDown > 0) {
                restockCoolDown--;
            } else {
                restockCoolDown = 12000;
                restockAll();
            }
            if (chatCoolDown > 0) {
                chatCoolDown--;
            } else {
                chatCoolDown = 10000;
                chatTicks = 4000;
            }
            if (chatTicks > 0) {
                chatTicks--;
                if (chatTarget == null) {
                    List<Boarwarf> availableChatTargets = level().getNearbyEntities(Boarwarf.class, TargetingConditions.DEFAULT, this, getBoundingBox().inflate(15));
                    for (Boarwarf boarwarf : availableChatTargets) {
                        if (boarwarf.chatTarget == null) {
                            boarwarf.chatTarget = this;
                            chatTarget = boarwarf;
                        }
                    }
                } else if (chatTarget.chatTicks <= 0 || (chatTarget.chatTarget == null || !chatTarget.chatTarget.getUUID().equals(getUUID()))) {
                    chatTarget = null;
                }
            }
        } else {
            this.idleAnimationState.startIfStopped(this.tickCount);
        }
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.customer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.customer;
    }

    public boolean hasCustomer() {
        return this.customer != null;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.addTrades();
        }

        return this.offers;
    }

    protected void addTrades() {
        if (getProfession() != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, getProfession().getTrades(this), 5);
        }
    }

    protected void addTrades(MerchantOffers original, VillagerTrades.ItemListing[] newTrades, int maxNumbers) {
        Set<Integer> set = Sets.newHashSet();
        if (newTrades.length > maxNumbers) {
            while (set.size() < maxNumbers) {
                set.add(this.random.nextInt(newTrades.length));
            }
        } else {
            for (int i = 0; i < newTrades.length; ++i) {
                set.add(i);
            }
        }

        for (Integer integer : set) {
            VillagerTrades.ItemListing villagertrades$itrade = newTrades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.random);
            if (merchantoffer != null) {
                original.add(merchantoffer);
            }
        }
    }

    protected void restockAll() {
        for (MerchantOffer merchantoffer : this.getOffers()) {
            merchantoffer.resetUses();
        }
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers offers) {

    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        if (offer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
        /*if (this.customer instanceof ServerPlayer) {
            ESCriteria.BOARWARF_TRADE.test((ServerPlayer)this.customer, this, offer.getResult());
        }*/
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {
        if (!this.level().isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getYesOrNoSound(!stack.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xp) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return ESSoundEvents.BOARWARF_YES.get();
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }
}
