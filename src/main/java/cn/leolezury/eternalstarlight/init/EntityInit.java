package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import cn.leolezury.eternalstarlight.entity.animal.Dryad;
import cn.leolezury.eternalstarlight.entity.animal.TwilightSquid;
import cn.leolezury.eternalstarlight.entity.attack.FireColumn;
import cn.leolezury.eternalstarlight.entity.attack.Vine;
import cn.leolezury.eternalstarlight.entity.attack.beam.StarlightGolemBeam;
import cn.leolezury.eternalstarlight.entity.boss.LunarMonstrosity;
import cn.leolezury.eternalstarlight.entity.boss.StarlightGolem;
import cn.leolezury.eternalstarlight.entity.boss.TheGatekeeper;
import cn.leolezury.eternalstarlight.entity.misc.*;
import cn.leolezury.eternalstarlight.entity.monster.LonestarSkeleton;
import cn.leolezury.eternalstarlight.entity.monster.NightshadeSpider;
import cn.leolezury.eternalstarlight.entity.npc.boarwarf.Boarwarf;
import cn.leolezury.eternalstarlight.entity.npc.boarwarf.golem.AstralGolem;
import cn.leolezury.eternalstarlight.entity.projectile.Spore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EternalStarlight.MOD_ID);
    public static final RegistryObject<EntityType<SLFallingBlock>> FALLING_BLOCK = ENTITIES.register("falling_block", () -> EntityType.Builder.<SLFallingBlock>of(SLFallingBlock::new, MobCategory.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20).build("falling_block"));
    public static final RegistryObject<EntityType<AethersentMeteor>> AETHERSENT_METEOR = ENTITIES.register("aethersent_meteor", () -> EntityType.Builder.<AethersentMeteor>of(AethersentMeteor::new, MobCategory.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20).build("aethersent_meteor"));
    public static final RegistryObject<EntityType<SLBoat>> BOAT = ENTITIES.register("boat", () -> EntityType.Builder.<SLBoat>of(SLBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).setShouldReceiveVelocityUpdates(true).build("boat"));
    public static final RegistryObject<EntityType<SLChestBoat>> CHEST_BOAT = ENTITIES.register("chest_boat", () -> EntityType.Builder.<SLChestBoat>of(SLChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).setShouldReceiveVelocityUpdates(true).build("chest_boat"));
    public static final RegistryObject<EntityType<CameraShake>> CAMERA_SHAKE = ENTITIES.register("camera_shake", () -> EntityType.Builder.<CameraShake>of(CameraShake::new, MobCategory.MISC).sized(0f, 0f).build(new ResourceLocation(EternalStarlight.MOD_ID, "camera_shake").toString()));
    public static final RegistryObject<EntityType<Boarwarf>> BOARWARF = ENTITIES.register("boarwarf", () -> EntityType.Builder.<Boarwarf>of(Boarwarf::new, MobCategory.MISC).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(EternalStarlight.MOD_ID, "boarwarf").toString()));
    public static final RegistryObject<EntityType<AstralGolem>> ASTRAL_GOLEM = ENTITIES.register("astral_golem", () -> EntityType.Builder.<AstralGolem>of(AstralGolem::new, MobCategory.MISC).sized(0.5F, 1.25F).build(new ResourceLocation(EternalStarlight.MOD_ID, "astral_golem").toString()));
    public static final RegistryObject<EntityType<LonestarSkeleton>> LONESTAR_SKELETON = ENTITIES.register("lonestar_skeleton", () -> EntityType.Builder.<LonestarSkeleton>of(LonestarSkeleton::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build(new ResourceLocation(EternalStarlight.MOD_ID, "lonestar_skeleton").toString()));
    public static final RegistryObject<EntityType<NightshadeSpider>> NIGHTSHADE_SPIDER = ENTITIES.register("nightshade_spider", () -> EntityType.Builder.<NightshadeSpider>of(NightshadeSpider::new, MobCategory.MONSTER).sized(0.7F, 0.5F).clientTrackingRange(8).build(new ResourceLocation(EternalStarlight.MOD_ID, "nightshade_spider").toString()));
    public static final RegistryObject<EntityType<TwilightSquid>> TWILIGHT_SQUID = ENTITIES.register("twilight_squid", () -> EntityType.Builder.<TwilightSquid>of(TwilightSquid::new, MobCategory.CREATURE).sized(0.7F, 0.5F).clientTrackingRange(8).build(new ResourceLocation(EternalStarlight.MOD_ID, "twilight_squid").toString()));
    public static final RegistryObject<EntityType<Dryad>> DRYAD = ENTITIES.register("dryad", () -> EntityType.Builder.<Dryad>of(Dryad::new, MobCategory.CREATURE).sized(0.7F, 0.3F).clientTrackingRange(8).build(new ResourceLocation(EternalStarlight.MOD_ID, "dryad").toString()));
    public static final RegistryObject<EntityType<EyeOfSeeking>> EYE_OF_SEEKING = ENTITIES.register("eye_of_seeking", () -> EntityType.Builder.<EyeOfSeeking>of(EyeOfSeeking::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(4).build(new ResourceLocation(EternalStarlight.MOD_ID, "eye_of_seeking").toString()));
    public static final RegistryObject<EntityType<TheGatekeeper>> THE_GATEKEEPER = ENTITIES.register("the_gatekeeper", () -> EntityType.Builder.<TheGatekeeper>of(TheGatekeeper::new, MobCategory.MISC).sized(0.6f, 1.99f).build(new ResourceLocation(EternalStarlight.MOD_ID, "the_gatekeeper").toString()));
    public static final RegistryObject<EntityType<StarlightGolem>> STARLIGHT_GOLEM = ENTITIES.register("starlight_golem", () -> EntityType.Builder.<StarlightGolem>of(StarlightGolem::new, MobCategory.MONSTER).sized(2.5f, 2.5f).fireImmune().build(new ResourceLocation(EternalStarlight.MOD_ID, "starlight_golem").toString()));
    public static final RegistryObject<EntityType<LunarMonstrosity>> LUNAR_MONSTROSITY = ENTITIES.register("lunar_monstrosity", () -> EntityType.Builder.<LunarMonstrosity>of(LunarMonstrosity::new, MobCategory.MONSTER).sized(1f, 3f).build(new ResourceLocation(EternalStarlight.MOD_ID, "lunar_monstrosity").toString()));
    public static final RegistryObject<EntityType<StarlightGolemBeam>> STARLIGHT_GOLEM_BEAM = ENTITIES.register("starlight_golem_beam", () -> EntityType.Builder.<StarlightGolemBeam>of(StarlightGolemBeam::new, MobCategory.MISC).sized(0f, 0f).build(new ResourceLocation(EternalStarlight.MOD_ID, "starlight_golem_beam").toString()));
    public static final RegistryObject<EntityType<FireColumn>> FIRE_COLUMN = ENTITIES.register("fire_column", () -> EntityType.Builder.<FireColumn>of(FireColumn::new, MobCategory.MISC).sized(0f, 0f).build(new ResourceLocation(EternalStarlight.MOD_ID, "fire_column").toString()));
    public static final RegistryObject<EntityType<Spore>> SPORE = ENTITIES.register("spore", () -> EntityType.Builder.<Spore>of(Spore::new, MobCategory.MISC).sized(0.3f, 0.3f).clientTrackingRange(6).updateInterval(1).build(new ResourceLocation(EternalStarlight.MOD_ID, "spore").toString()));
    public static final RegistryObject<EntityType<Vine>> VINE = ENTITIES.register("vine", () -> EntityType.Builder.<Vine>of(Vine::new, MobCategory.MISC).sized(0.3f, 1f).build(new ResourceLocation(EternalStarlight.MOD_ID, "vine").toString()));
}
