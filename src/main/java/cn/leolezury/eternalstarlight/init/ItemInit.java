package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import cn.leolezury.eternalstarlight.entity.misc.SLBoat;
import cn.leolezury.eternalstarlight.item.armor.SLArmorMaterials;
import cn.leolezury.eternalstarlight.item.armor.SwampSilverArmorItem;
import cn.leolezury.eternalstarlight.item.misc.LootBagItem;
import cn.leolezury.eternalstarlight.item.misc.SLBoatItem;
import cn.leolezury.eternalstarlight.item.misc.SeekingEyeItem;
import cn.leolezury.eternalstarlight.item.weapon.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemInit implements ModInitializer {
    //public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EternalStarlight.MOD_ID);
    //static Rarity STARLIGHT = Rarity.create("Starlight", ChatFormatting.DARK_AQUA);
    public static final Item RED_STARLIGHT_CRYSTAL_BLOCK = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "red_starlight_crystal_block"),new BlockItem(BlockInit.RED_STARLIGHT_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item BLUE_STARLIGHT_CRYSTAL_BLOCK = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "blue_starlight_crystal_block"),new BlockItem(BlockInit.BLUE_STARLIGHT_CRYSTAL_BLOCK, new FabricItemSettings()));
    public static final Item RED_STARLIGHT_CRYSTAL_CLUSTER = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "red_starlight_crystal_cluster"),new BlockItem(BlockInit.RED_STARLIGHT_CRYSTAL_CLUSTER, new FabricItemSettings()));
    public static final Item BLUE_STARLIGHT_CRYSTAL_CLUSTER = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "blue_starlight_crystal_cluster"),new BlockItem(BlockInit.BLUE_STARLIGHT_CRYSTAL_CLUSTER, new FabricItemSettings()));
    public static final Item RED_CRYSTAL_MOSS_CARPET = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "red_crystal_moss_carpet"), new BlockItem(BlockInit.RED_CRYSTAL_MOSS_CARPET, new FabricItemSettings()));
    public static final Item BLUE_CRYSTAL_MOSS_CARPET = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "blue_crystal_moss_carpet"),new BlockItem(BlockInit.BLUE_CRYSTAL_MOSS_CARPET, new FabricItemSettings()));
    public static final Item RED_STARLIGHT_CRYSTAL_SHARD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "red_starlight_crystal_shard"),new Item(new FabricItemSettings()));
    public static final Item BLUE_STARLIGHT_CRYSTAL_SHARD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "blue_starlight_crystal_shard"),new Item(new FabricItemSettings()));
    public static final Item CRYSTAL_CROSSBOW = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "crystal_crossbow"), new CrystalCrossbowItem(new FabricItemSettings().maxCount(1).maxDamage(2000).rarity(STARLIGHT)));
    public static final Item LUNAR_MONSTROSITY_BOW = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_monstrosity_bow"), new LunarMonstrosityBowItem(new FabricItemSettings().maxCount(1).maxDamage(2000).rarity(STARLIGHT)));
    public static final Item LUNAR_BERRIES = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_berries"), new AliasedBlockItem(BlockInit.BERRIES_VINES,new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).build())));

    //lunar wood
    public static final Item LUNAR_SAPLING = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_sapling"),new BlockItem(BlockInit.LUNAR_SAPLING,new FabricItemSettings()));
    public static final Item LUNAR_LEAVES = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_leaves"),new BlockItem(BlockInit.LUNAR_LEAVES,new FabricItemSettings()));
    public static final Item LUNAR_LOG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_log"),new BlockItem(BlockInit.LUNAR_LOG,new FabricItemSettings()));
    public static final Item LUNAR_WOOD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_wood"),new BlockItem(BlockInit.LUNAR_WOOD,new FabricItemSettings()));
    public static final Item LUNAR_PLANKS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_planks"),new BlockItem(BlockInit.LUNAR_PLANKS,new FabricItemSettings()));
    public static final Item STRIPPED_LUNAR_LOG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "stripped_lunar_log"),new BlockItem(BlockInit.STRIPPED_LUNAR_LOG,new FabricItemSettings()));
    public static final Item STRIPPED_LUNAR_WOOD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "stripped_lunar_wood"),new BlockItem(BlockInit.STRIPPED_LUNAR_WOOD,new FabricItemSettings()));
    public static final Item LUNAR_DOOR = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_door"),new BlockItem(BlockInit.LUNAR_DOOR,new FabricItemSettings()));
    public static final Item LUNAR_TRAPDOOR = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_trapdoor"),new BlockItem(BlockInit.LUNAR_TRAPDOOR,new FabricItemSettings()));
    public static final Item LUNAR_PRESSURE_PLATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_pressure_plate"),new BlockItem(BlockInit.LUNAR_PRESSURE_PLATE,new FabricItemSettings()));
    public static final Item LUNAR_BUTTON = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_button"),new BlockItem(BlockInit.LUNAR_BUTTON,new FabricItemSettings()));
    public static final Item LUNAR_FENCE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_fence"),new BlockItem(BlockInit.LUNAR_FENCE,new FabricItemSettings()));
    public static final Item LUNAR_FENCE_GATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_fence_gate"),new BlockItem(BlockInit.LUNAR_FENCE_GATE,new FabricItemSettings()));
    public static final Item LUNAR_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_slab"),new BlockItem(BlockInit.LUNAR_SLAB,new FabricItemSettings()));
    public static final Item LUNAR_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_stairs"),new BlockItem(BlockInit.LUNAR_STAIRS,new FabricItemSettings()));
    public static final Item LUNAR_SIGN = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_sign"),new SignItem(new FabricItemSettings().maxCount(32) ,BlockInit.LUNAR_SIGN, BlockInit.LUNAR_WALL_SIGN));
    public static final Item LUNAR_HANGING_SIGN = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_hanging_sign"), new HangingSignItem(BlockInit.LUNAR_HANGING_SIGN, BlockInit.LUNAR_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(32)));
    public static final Item LUNAR_BOAT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_boat"),new SLBoatItem(false, SLBoat.Type.LUNAR, new FabricItemSettings().maxCount(1)));
    public static final Item LUNAR_CHEST_BOAT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_chest_boat"),new SLBoatItem(true, SLBoat.Type.LUNAR, new FabricItemSettings().maxCount(1)));

    //northland wood
    public static final Item NORTHLAND_SAPLING = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_sapling"),new BlockItem(BlockInit.NORTHLAND_SAPLING,new FabricItemSettings()));
    public static final Item NORTHLAND_LEAVES = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_leaves"),new BlockItem(BlockInit.NORTHLAND_LEAVES,new FabricItemSettings()));
    public static final Item NORTHLAND_LOG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_log"),new BlockItem(BlockInit.NORTHLAND_LOG,new FabricItemSettings()));
    public static final Item NORTHLAND_WOOD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_wood"),new BlockItem(BlockInit.NORTHLAND_WOOD,new FabricItemSettings()));
    public static final Item NORTHLAND_PLANKS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_planks"),new BlockItem(BlockInit.NORTHLAND_PLANKS,new FabricItemSettings()));
    public static final Item STRIPPED_NORTHLAND_LOG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "stripped_northland_log"),new BlockItem(BlockInit.STRIPPED_NORTHLAND_LOG,new FabricItemSettings()));
    public static final Item STRIPPED_NORTHLAND_WOOD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "stripped_northland_wood"),new BlockItem(BlockInit.STRIPPED_NORTHLAND_WOOD,new FabricItemSettings()));
    public static final Item NORTHLAND_DOOR = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_door"),new BlockItem(BlockInit.NORTHLAND_DOOR,new FabricItemSettings()));
    public static final Item NORTHLAND_TRAPDOOR = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_trapdoor"),new BlockItem(BlockInit.NORTHLAND_TRAPDOOR,new FabricItemSettings()));
    public static final Item NORTHLAND_PRESSURE_PLATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_pressure_plate"),new BlockItem(BlockInit.NORTHLAND_PRESSURE_PLATE,new FabricItemSettings()));
    public static final Item NORTHLAND_BUTTON = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_button"),new BlockItem(BlockInit.NORTHLAND_BUTTON,new FabricItemSettings()));
    public static final Item NORTHLAND_FENCE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_fence"),new BlockItem(BlockInit.NORTHLAND_FENCE,new FabricItemSettings()));
    public static final Item NORTHLAND_FENCE_GATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_fence_gate"),new BlockItem(BlockInit.NORTHLAND_FENCE_GATE,new FabricItemSettings()));
    public static final Item NORTHLAND_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_slab"),new BlockItem(BlockInit.NORTHLAND_SLAB,new FabricItemSettings()));
    public static final Item NORTHLAND_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_stairs"),new BlockItem(BlockInit.NORTHLAND_STAIRS,new FabricItemSettings()));
    public static final Item NORTHLAND_SIGN = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_sign"),new  SignItem(new FabricItemSettings().maxCount(32), BlockInit.NORTHLAND_SIGN, BlockInit.NORTHLAND_WALL_SIGN));
    public static final Item NORTHLAND_HANGING_SIGN = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_hanging_sign"), new HangingSignItem(BlockInit.NORTHLAND_HANGING_SIGN, BlockInit.NORTHLAND_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(32)));
    public static final Item NORTHLAND_BOAT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_boat"), new SLBoatItem(false, SLBoat.Type.NORTHLAND, new FabricItemSettings().maxCount(1)));
    public static final Item NORTHLAND_CHEST_BOAT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "northland_chest_boat"), new SLBoatItem(true, SLBoat.Type.NORTHLAND, new FabricItemSettings().maxCount(1)));

    //starlight mangrove wood
    public static final Item STARLIGHT_MANGROVE_SAPLING = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_sapling"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_SAPLING,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_LEAVES = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_leaves"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_LEAVES,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_LOG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_log"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_LOG,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_WOOD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_wood"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_WOOD,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_PLANKS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_planks"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_PLANKS,new FabricItemSettings()));
    public static final Item STRIPPED_STARLIGHT_MANGROVE_LOG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "stripped_starlight_mangrove_log"),new BlockItem(BlockInit.STRIPPED_STARLIGHT_MANGROVE_LOG,new FabricItemSettings()));
    public static final Item STRIPPED_STARLIGHT_MANGROVE_WOOD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "stripped_starlight_mangrove_wood"),new BlockItem(BlockInit.STRIPPED_STARLIGHT_MANGROVE_WOOD,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_DOOR = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_door"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_DOOR,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_TRAPDOOR = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_trapdoor"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_TRAPDOOR,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_PRESSURE_PLATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_pressure_plate"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_PRESSURE_PLATE,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_BUTTON = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_button"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_BUTTON,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_FENCE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_fence"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_FENCE,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_FENCE_GATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_fence_gate"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_FENCE_GATE,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_slab"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_SLAB,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_stairs"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_STAIRS,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_ROOTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_roots"),new BlockItem(BlockInit.STARLIGHT_MANGROVE_ROOTS,new FabricItemSettings()));
    public static final Item MUDDY_STARLIGHT_MANGROVE_ROOTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "muddy_starlight_mangrove_roots"),new BlockItem(BlockInit.MUDDY_STARLIGHT_MANGROVE_ROOTS,new FabricItemSettings()));
    public static final Item STARLIGHT_MANGROVE_SIGN = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_sign"),new SignItem(new FabricItemSettings().maxCount(32), BlockInit.STARLIGHT_MANGROVE_SIGN, BlockInit.STARLIGHT_MANGROVE_WALL_SIGN));
    public static final Item STARLIGHT_MANGROVE_HANGING_SIGN = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_hanging_sign"), new HangingSignItem(BlockInit.STARLIGHT_MANGROVE_HANGING_SIGN, BlockInit.STARLIGHT_MANGROVE_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(32)));
    public static final Item STARLIGHT_MANGROVE_BOAT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_boat"), new SLBoatItem(false, SLBoat.Type.STARLIGHT_MANGROVE, new FabricItemSettings().maxCount(1)));
    public static final Item STARLIGHT_MANGROVE_CHEST_BOAT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_mangrove_chest_boat"), new SLBoatItem(true, SLBoat.Type.STARLIGHT_MANGROVE, new FabricItemSettings().maxCount(1)));


    public static final Item STARLIGHT_FLOWER = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_flower"),new BlockItem(BlockInit.STARLIGHT_FLOWER,new FabricItemSettings()));
    public static final Item NIGHT_SPROUTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "night_sprouts"),new BlockItem(BlockInit.NIGHT_SPROUTS,new FabricItemSettings()));
    public static final Item GLOWING_NIGHT_SPROUTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_night_sprouts"),new BlockItem(BlockInit.GLOWING_NIGHT_SPROUTS,new FabricItemSettings()));
    public static final Item SMALL_NIGHT_SPROUTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "small_night_sprouts"),new BlockItem(BlockInit.SMALL_NIGHT_SPROUTS,new FabricItemSettings()));
    public static final Item SMALL_GLOWING_NIGHT_SPROUTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "small_glowing_night_sprouts"),new BlockItem(BlockInit.SMALL_GLOWING_NIGHT_SPROUTS,new FabricItemSettings()));
    public static final Item LUNAR_GRASS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_grass"),new BlockItem(BlockInit.LUNAR_GRASS,new FabricItemSettings()));
    public static final Item GLOWING_LUNAR_GRASS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_lunar_grass"),new BlockItem(BlockInit.GLOWING_LUNAR_GRASS,new FabricItemSettings()));
    public static final Item CRESCENT_GRASS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "crescent_grass"),new BlockItem(BlockInit.CRESCENT_GRASS,new FabricItemSettings()));
    public static final Item GLOWING_CRESCENT_GRASS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_crescent_grass"),new BlockItem(BlockInit.GLOWING_CRESCENT_GRASS,new FabricItemSettings()));
    public static final Item PARASOL_GRASS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "parasol_grass"),new BlockItem(BlockInit.PARASOL_GRASS,new FabricItemSettings()));
    public static final Item GLOWING_PARASOL_GRASS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_parasol_grass"),new BlockItem(BlockInit.GLOWING_PARASOL_GRASS,new FabricItemSettings()));
    public static final Item LUNAR_REED = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_reed"),new BlockItem(BlockInit.LUNAR_REED,new FabricItemSettings()));
    public static final Item GLOWING_MUSHROOM = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_mushroom"),new BlockItem(BlockInit.GLOWING_MUSHROOM,new FabricItemSettings()));
    public static final Item GLOWING_MUSHROOM_BLOCK = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_mushroom_block"),new BlockItem(BlockInit.GLOWING_MUSHROOM_BLOCK,new FabricItemSettings()));
    public static final Item NIGHTSHADE_GRASS_BLOCK = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_grass_block"),new BlockItem(BlockInit.NIGHTSHADE_GRASS_BLOCK,new FabricItemSettings()));
    public static final Item NIGHTSHADE_DIRT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_dirt"),new BlockItem(BlockInit.NIGHTSHADE_DIRT,new FabricItemSettings()));

    //grimstone
    public static final Item GRIMSTONE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "grimstone"),new BlockItem(BlockInit.GRIMSTONE,new FabricItemSettings()));
    public static final Item GRIMSTONE_BRICKS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "grimstone_bricks"),new BlockItem(BlockInit.GRIMSTONE_BRICKS,new FabricItemSettings()));
    public static final Item GRIMSTONE_BRICK_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "grimstone_brick_slab"),new BlockItem(BlockInit.GRIMSTONE_BRICK_SLAB,new FabricItemSettings()));
    public static final Item GRIMSTONE_BRICK_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "grimstone_brick_stairs"),new BlockItem(BlockInit.GRIMSTONE_BRICK_STAIRS,new FabricItemSettings()));
    public static final Item GRIMSTONE_BRICK_WALL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "grimstone_brick_wall"),new BlockItem(BlockInit.GRIMSTONE_BRICK_WALL,new FabricItemSettings()));
    public static final Item POLISHED_GRIMSTONE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_grimstone"),new BlockItem(BlockInit.POLISHED_GRIMSTONE,new FabricItemSettings()));
    public static final Item POLISHED_GRIMSTONE_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_grimstone_slab"),new BlockItem(BlockInit.POLISHED_GRIMSTONE_SLAB,new FabricItemSettings()));
    public static final Item POLISHED_GRIMSTONE_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_grimstone_stairs"),new BlockItem(BlockInit.POLISHED_GRIMSTONE_STAIRS,new FabricItemSettings()));
    public static final Item POLISHED_GRIMSTONE_WALL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_grimstone_wall"),new BlockItem(BlockInit.POLISHED_GRIMSTONE_WALL,new FabricItemSettings()));
    public static final Item CHISELED_GRIMSTONE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "chiseled_grimstone"),new BlockItem(BlockInit.CHISELED_GRIMSTONE,new FabricItemSettings()));

    //voidstone
    public static final Item VOIDSTONE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "voidstone"),new BlockItem(BlockInit.VOIDSTONE,new FabricItemSettings()));
    public static final Item VOIDSTONE_BRICKS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "voidstone_bricks"),new BlockItem(BlockInit.VOIDSTONE_BRICKS,new FabricItemSettings()));
    public static final Item VOIDSTONE_BRICK_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "voidstone_brick_slab"),new BlockItem(BlockInit.VOIDSTONE_BRICK_SLAB,new FabricItemSettings()));
    public static final Item VOIDSTONE_BRICK_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "voidstone_brick_stairs"),new BlockItem(BlockInit.VOIDSTONE_BRICK_STAIRS,new FabricItemSettings()));
    public static final Item VOIDSTONE_BRICK_WALL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "voidstone_brick_wall"),new BlockItem(BlockInit.VOIDSTONE_BRICK_WALL,new FabricItemSettings()));
    public static final Item POLISHED_VOIDSTONE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_voidstone"),new BlockItem(BlockInit.POLISHED_VOIDSTONE,new FabricItemSettings()));
    public static final Item POLISHED_VOIDSTONE_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_voidstone_slab"),new BlockItem(BlockInit.POLISHED_VOIDSTONE_SLAB,new FabricItemSettings()));
    public static final Item POLISHED_VOIDSTONE_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_voidstone_stairs"),new BlockItem(BlockInit.POLISHED_VOIDSTONE_STAIRS,new FabricItemSettings()));
    public static final Item POLISHED_VOIDSTONE_WALL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "polished_voidstone_wall"),new BlockItem(BlockInit.POLISHED_VOIDSTONE_WALL,new FabricItemSettings()));
    public static final Item CHISELED_VOIDSTONE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "chiseled_voidstone"),new BlockItem(BlockInit.CHISELED_VOIDSTONE,new FabricItemSettings()));

    //mud
    public static final Item NIGHTSHADE_MUD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_mud"),new BlockItem(BlockInit.NIGHTSHADE_MUD,new FabricItemSettings()));
    public static final Item GLOWING_NIGHTSHADE_MUD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "glowing_nightshade_mud"),new BlockItem(BlockInit.GLOWING_NIGHTSHADE_MUD,new FabricItemSettings()));
    public static final Item PACKED_NIGHTSHADE_MUD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "packed_nightshade_mud"),new BlockItem(BlockInit.PACKED_NIGHTSHADE_MUD,new FabricItemSettings()));
    public static final Item NIGHTSHADE_MUD_BRICKS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_mud_bricks"),new BlockItem(BlockInit.NIGHTSHADE_MUD_BRICKS,new FabricItemSettings()));
    public static final Item NIGHTSHADE_MUD_BRICK_SLAB = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_mud_brick_slab"),new BlockItem(BlockInit.NIGHTSHADE_MUD_BRICK_SLAB,new FabricItemSettings()));
    public static final Item NIGHTSHADE_MUD_BRICK_STAIRS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_mud_brick_stairs"),new BlockItem(BlockInit.NIGHTSHADE_MUD_BRICK_STAIRS,new FabricItemSettings()));
    public static final Item NIGHTSHADE_MUD_BRICK_WALL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "nightshade_mud_brick_wall"),new BlockItem(BlockInit.NIGHTSHADE_MUD_BRICK_WALL,new FabricItemSettings()));

    public static final Item ENERGY_BLOCK = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "energy_block"),new BlockItem(BlockInit.ENERGY_BLOCK,new FabricItemSettings()));
    public static final Item STARLIGHT_GOLEM_SPAWNER = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "starlight_golem_spawner"),new BlockItem(BlockInit.STARLIGHT_GOLEM_SPAWNER,new FabricItemSettings()));
    public static final Item LUNAR_MONSTROSITY_SPAWNER = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "lunar_monstrosity_spawner"),new BlockItem(BlockInit.LUNAR_MONSTROSITY_SPAWNER,new FabricItemSettings()));
    public static final Item LOOT_BAG = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "loot_bag"), new LootBagItem(new FabricItemSettings().fireproof().rarity(STARLIGHT)));
    public static final Item SWAMP_SILVER_ORE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_ore"),new BlockItem(BlockInit.SWAMP_SILVER_ORE,new FabricItemSettings()));
    public static final Item SWAMP_SILVER_BLOCK = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_block"),new BlockItem(BlockInit.SWAMP_SILVER_BLOCK,new FabricItemSettings()));
    public static final Item SWAMP_SILVER_INGOT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_ingot"),new Item(new FabricItemSettings()));
    public static final Item SWAMP_SILVER_NUGGET = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_nugget"),new Item(new FabricItemSettings()));
    public static final Item SWAMP_SILVER_SWORD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_sword", new SwordItem(SLItemTiers.SWAMP_SILVER, 3, -2.4F, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_PICKAXE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_pickaxe", new PickaxeItem(SLItemTiers.SWAMP_SILVER,1, -1.0F, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_AXE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_axe", new AxeItem(SLItemTiers.SWAMP_SILVER, 6, -3.1F, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_SCYTHE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_scythe", new ScytheItem(SLItemTiers.SWAMP_SILVER, 3, -1.0F, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_HELMET = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_helmet", new SwampSilverArmorItem(SLArmorMaterials.SWAMP_SILVER, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_CHESTPLATE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_chestplate", new SwampSilverArmorItem(SLArmorMaterials.SWAMP_SILVER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_LEGGINGS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_leggings", new SwampSilverArmorItem(SLArmorMaterials.SWAMP_SILVER, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item SWAMP_SILVER_BOOTS = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "swamp_silver_boots", new SwampSilverArmorItem(SLArmorMaterials.SWAMP_SILVER, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item GOLEM_STEEL_INGOT = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "golem_steel_ingot"),new Item(new FabricItemSettings().rarity(STARLIGHT)));
    public static final Item OXIDIZED_GOLEM_STEEL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "oxidized_golem_steel"),new Item(new FabricItemSettings()));
    public static final Item TENACIOUS_PETAL = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "tenacious_petal"),new Item(new FabricItemSettings().rarity(STARLIGHT)));
    public static final Item PETAL_GREATSWORD = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "petal_greatsword"),new GreatswordItem(SLItemTiers.PETAL, 6, -2.8F, new FabricItemSettings().rarity(STARLIGHT)));
    public static final Item PETAL_SCYTHE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "petal_scythe"),new  ScytheItem(SLItemTiers.PETAL, 3, -1.0F, new FabricItemSettings().rarity(STARLIGHT)));
    public static final Item SEEKING_EYE = Registry.register(Registries.ITEM,new Identifier(EternalStarlight.MODID, "seeking_eye"),new  SeekingEyeItem(new FabricItemSettings());

    @Override
    public void onInitialize() {

    }
}
