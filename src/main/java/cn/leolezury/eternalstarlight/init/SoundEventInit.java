package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundEventInit implements ModInitializer {

    public static final SoundEvent ARMOR_EQUIP_SWAMP_SILVER = SoundEvent.of(Identifier.tryParse("item.armor.equip_swamp_silver"));

    public static final SoundEvent SEEKING_EYE_LAUNCH = SoundEvent.of(Identifier.tryParse("entity.seeking_eye.launch"));
    public static final SoundEvent SEEKING_EYE_DEATH = SoundEvent.of(Identifier.tryParse("entity.seeking_eye.death"));

    public static final SoundEvent BOARWARF_AMBIENT = SoundEvent.of(Identifier.tryParse("entity.boarwarf.ambient"));
    public static final SoundEvent BOARWARF_HURT = SoundEvent.of(Identifier.tryParse("entity.boarwarf.hurt"));
    public static final SoundEvent BOARWARF_DEATH = SoundEvent.of(Identifier.tryParse("entity.boarwarf.death"));
    public static final SoundEvent BOARWARF_TRADE = SoundEvent.of(Identifier.tryParse("entity.boarwarf.trade"));
    public static final SoundEvent BOARWARF_YES = SoundEvent.of(Identifier.tryParse("entity.boarwarf.yes"));
    public static final SoundEvent BOARWARF_NO = SoundEvent.of(Identifier.tryParse("entity.boarwarf.no"));


    public static final SoundEvent ASTRAL_GOLEM_ATTACK = SoundEvent.of(Identifier.tryParse("entity.astral_golem.attack"));
    public static final SoundEvent ASTRAL_GOLEM_HURT = SoundEvent.of(Identifier.tryParse("entity.astral_golem.hurt"));
    public static final SoundEvent ASTRAL_GOLEM_DEATH = SoundEvent.of(Identifier.tryParse("entity.astral_golem.death"));
    public static final SoundEvent ASTRAL_GOLEM_REPAIR = SoundEvent.of(Identifier.tryParse("entity.astral_golem.repair"));

    public static final SoundEvent LONESTAR_SKELETON_AMBIENT = SoundEvent.of(Identifier.tryParse("entity.lonestar_skeleton.ambient"));
    public static final SoundEvent LONESTAR_SKELETON_HURT = SoundEvent.of(Identifier.tryParse("entity.lonestar_skeleton.hurt"));
    public static final SoundEvent LONESTAR_SKELETON_DEATH = SoundEvent.of(Identifier.tryParse("entity.lonestar_skeleton.death"));
    public static final SoundEvent LONESTAR_SKELETON_STEP = SoundEvent.of(Identifier.tryParse("entity.lonestar_skeleton.step"));

    public static final SoundEvent DRYAD_HURT = SoundEvent.of(Identifier.tryParse("entity.dryad.hurt"));

    public static final SoundEvent TWILIGHT_SQUID_HURT = SoundEvent.of(Identifier.tryParse("entity.twilight_squid.hurt"));
    public static final SoundEvent TWILIGHT_SQUID_DEATH = SoundEvent.of(Identifier.tryParse("entity.twilight_squid.death"));
    public static final SoundEvent TWILIGHT_SQUID_SQUIRT = SoundEvent.of(Identifier.tryParse("entity.twilight_squid.squirt"));

    public static final SoundEvent STARLIGHT_GOLEM_HURT = SoundEvent.of(Identifier.tryParse("entity.starlight_golem.hurt"));
    public static final SoundEvent STARLIGHT_GOLEM_DEATH = SoundEvent.of(Identifier.tryParse("entity.starlight_golem.death"));
    public static final SoundEvent STARLIGHT_GOLEM_BLOCK = SoundEvent.of(Identifier.tryParse("entity.starlight_golem.block"));
    public static final SoundEvent STARLIGHT_GOLEM_PREPARE_BEAM = SoundEvent.of(Identifier.tryParse("entity.starlight_golem.prepare_beam"));
    public static final SoundEvent STARLIGHT_GOLEM_PREPARE_CHARGE = SoundEvent.of(Identifier.tryParse("entity.starlight_golem.prepare_charge"));

    public static final SoundEvent FIRE_COLUMN_APPEAR = SoundEvent.of(Identifier.tryParse("entity.fire_column.appear"));

    public static final SoundEvent LUNAR_MONSTROSITY_HURT = SoundEvent.of(Identifier.tryParse("entity.lunar_monstrosity.hurt"));
    public static final SoundEvent LUNAR_MONSTROSITY_DEATH = SoundEvent.of(Identifier.tryParse("entity.lunar_monstrosity.death"));
    public static final SoundEvent LUNAR_MONSTROSITY_BITE = SoundEvent.of(Identifier.tryParse("entity.lunar_monstrosity.bite"));
    public static final SoundEvent LUNAR_MONSTROSITY_ROAR = SoundEvent.of(Identifier.tryParse("entity.lunar_monstrosity.roar"));

    @Override
    public void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, ARMOR_EQUIP_SWAMP_SILVER);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, SEEKING_EYE_LAUNCH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, SEEKING_EYE_DEATH);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, BOARWARF_AMBIENT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, BOARWARF_HURT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, BOARWARF_DEATH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, BOARWARF_TRADE);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, BOARWARF_YES);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, BOARWARF_NO);


        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, ASTRAL_GOLEM_ATTACK);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, ASTRAL_GOLEM_HURT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, ASTRAL_GOLEM_DEATH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, ASTRAL_GOLEM_REPAIR);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LONESTAR_SKELETON_AMBIENT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LONESTAR_SKELETON_HURT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LONESTAR_SKELETON_DEATH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LONESTAR_SKELETON_STEP);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, DRYAD_HURT);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, TWILIGHT_SQUID_HURT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, TWILIGHT_SQUID_DEATH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, TWILIGHT_SQUID_SQUIRT);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, STARLIGHT_GOLEM_HURT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, STARLIGHT_GOLEM_DEATH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, STARLIGHT_GOLEM_BLOCK);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, STARLIGHT_GOLEM_PREPARE_BEAM);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, STARLIGHT_GOLEM_PREPARE_CHARGE);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, FIRE_COLUMN_APPEAR);

        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LUNAR_MONSTROSITY_HURT);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LUNAR_MONSTROSITY_DEATH);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LUNAR_MONSTROSITY_BITE);
        Registry.register(Registries.SOUND_EVENT, EternalStarlight.MODID, LUNAR_MONSTROSITY_ROAR);
    }
}
