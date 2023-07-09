package cn.leolezury.eternalstarlight.init;

import cn.leolezury.eternalstarlight.EternalStarlight;
import cn.leolezury.eternalstarlight.block.*;
import cn.leolezury.eternalstarlight.block.entity.SLWoodTypes;
import cn.leolezury.eternalstarlight.world.feature.tree.LunarTreeGrower;
import cn.leolezury.eternalstarlight.world.feature.tree.NorthlandTreeGrower;
import cn.leolezury.eternalstarlight.world.feature.tree.StarlightMangroveTreeGrower;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.CaveVines;
import net.minecraft.block.MapColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    // public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EternalStarlight.MOD_ID);
    public static final Block BERRIES_VINES = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "berries_vines"),new BerriesVineBlock(FabricBlockSettings.create().mapColor(MapColor.LIGHT_BLUE).ticksRandomly().noCollision().luminance(CaveVines.getLuminanceSupplier(14)).breakInstantly().sounds(BlockSoundGroup.CAVE_VINES)));
    public static final Block BERRIES_VINES_PLANT = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "berries_vines_plant") BerriesVinePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().lightLevel(CaveVines.emission(14)).instabreak().sound(SoundType.CAVE_VINES)));
    public static final Block RED_STARLIGHT_CRYSTAL_CLUSTER = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "red_starlight_crystal_cluster") StarlightCrystalClusterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.5F).requiresCorrectToolForDrops().noOcclusion().lightLevel((state) -> {
        return 15;
    }).sound(SoundType.AMETHYST)));
    public static final Block BLUE_STARLIGHT_CRYSTAL_CLUSTER = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "blue_starlight_crystal_cluster") StarlightCrystalClusterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(1.5F).requiresCorrectToolForDrops().noOcclusion().lightLevel((state) -> {
        return 15;
    }).sound(SoundType.AMETHYST)));
    public static final Block RED_STARLIGHT_CRYSTAL_BLOCK = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "red_starlight_crystal_block") Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.5F).requiresCorrectToolForDrops().lightLevel((state) -> {
        return 15;
    }).sound(SoundType.AMETHYST)));
    public static final Block BLUE_STARLIGHT_CRYSTAL_BLOCK = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "blue_starlight_crystal_block") Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(1.5F).requiresCorrectToolForDrops().lightLevel((state) -> {
        return 15;
    }).sound(SoundType.AMETHYST)));
    public static final Block RED_CRYSTAL_MOSS_CARPET = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "red_crystal_moss_carpet") CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS_CARPET).lightLevel((state) -> {
        return 15;
    })));
    public static final Block BLUE_CRYSTAL_MOSS_CARPET = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "blue_crystal_moss_carpet") CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.1F).sound(SoundType.MOSS_CARPET).lightLevel((state) -> {
        return 15;
    })));

    //lunar wood
    public static final Block LUNAR_LEAVES = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_LIGHT_BLUE)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }
                @Override
                public int getFlammability(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }
            });
    public static final Block LUNAR_LOG = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_log",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_BLACK)));
    public static final Block LUNAR_WOOD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_wood",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_BLACK)));
    public static final Block LUNAR_PLANKS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_BLACK)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }
                @Override
                public int getFlammability(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }
            });
    public static final Block STRIPPED_LUNAR_LOG = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "stripped_lunar_log",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.COLOR_BLACK)));
    public static final Block STRIPPED_LUNAR_WOOD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "stripped_lunar_wood",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.COLOR_BLACK)));
    public static final Block LUNAR_DOOR = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_door",
            () -> new SLDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR_SET));
    public static final Block LUNAR_TRAPDOOR = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_trapdoor",
            () -> new SLTrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR_SET));
    public static final Block LUNAR_PRESSURE_PLATE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_pressure_plate",
            () -> new PressurePlateBlock(SLPressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR_SET));
    public static final Block LUNAR_BUTTON = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_button",
            () -> new SLButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR_SET, 30, true));
    public static final Block LUNAR_FENCE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_BLACK)));
    public static final Block LUNAR_FENCE_GATE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR));
    public static final Block LUNAR_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_BLACK)));
    public static final Block LUNAR_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_stairs",
            () -> new StairBlock(() -> LUNAR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).mapColor(MapColor.COLOR_BLACK)));
    public static final Block LUNAR_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_sign",
            () -> new SLStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR));
    public static final Block LUNAR_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_wall_sign",
            () -> new SLWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR));
    public static final Block LUNAR_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_hanging_sign",
            () -> new SLCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR));
    public static final Block LUNAR_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_wall_hanging_sign",
            () -> new SLWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(MapColor.COLOR_BLACK), SLWoodTypes.LUNAR));
    public static final Block LUNAR_SAPLING = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_sapling") SaplingBlock(new LunarTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POTTED_LUNAR_SAPLING = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "potted_lunar_sapling") FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), LUNAR_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    //northland wood
    public static final Block NORTHLAND_LEAVES = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_LIGHT_BLUE)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }
                @Override
                public int getFlammability(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }
            });
    public static final Block NORTHLAND_LOG = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_log",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_BROWN)));
    public static final Block NORTHLAND_WOOD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_wood",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_BROWN)));
    public static final Block NORTHLAND_PLANKS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_BROWN)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }
                @Override
                public int getFlammability(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }
            });
    public static final Block STRIPPED_NORTHLAND_LOG = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "stripped_northland_log",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.COLOR_BROWN)));
    public static final Block STRIPPED_NORTHLAND_WOOD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "stripped_northland_wood",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.COLOR_BROWN)));
    public static final Block NORTHLAND_DOOR = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_door",
            () -> new SLDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND_SET));
    public static final Block NORTHLAND_TRAPDOOR = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_trapdoor",
            () -> new SLTrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND_SET));
    public static final Block NORTHLAND_PRESSURE_PLATE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_pressure_plate",
            () -> new SLPressurePlateBlock(SLPressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND_SET));
    public static final Block NORTHLAND_BUTTON = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_button",
            () -> new SLButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND_SET, 30, true));
    public static final Block NORTHLAND_FENCE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_BROWN)));
    public static final Block NORTHLAND_FENCE_GATE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND));
    public static final Block NORTHLAND_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_BROWN)));
    public static final Block NORTHLAND_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_stairs",
            () -> new StairBlock(() -> NORTHLAND_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).mapColor(MapColor.COLOR_BROWN)));
    public static final Block NORTHLAND_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_sign",
            () -> new SLStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND));
    public static final Block NORTHLAND_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_wall_sign",
            () -> new SLWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND));
    public static final Block NORTHLAND_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_hanging_sign",
            () -> new SLCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND));
    public static final Block NORTHLAND_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_wall_hanging_sign",
            () -> new SLWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(MapColor.COLOR_BROWN), SLWoodTypes.NORTHLAND));
    public static final Block NORTHLAND_SAPLING = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "northland_sapling") SaplingBlock(new NorthlandTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POTTED_NORTHLAND_SAPLING = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "potted_northland_sapling") FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), NORTHLAND_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    //starlight mangrove wood
    public static final Block STARLIGHT_MANGROVE_LEAVES = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }
                @Override
                public int getFlammability(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }
            });
    public static final Block STARLIGHT_MANGROVE_LOG = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_log",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(MapColor.COLOR_RED)));
    public static final Block STARLIGHT_MANGROVE_WOOD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_wood",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_RED)));
    public static final Block STARLIGHT_MANGROVE_PLANKS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_RED)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }
                @Override
                public int getFlammability(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
                @Override
                public int getFireSpreadSpeed(BlockState state,BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }
            });
    public static final Block STRIPPED_STARLIGHT_MANGROVE_LOG = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "stripped_starlight_mangrove_log",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.COLOR_RED)));
    public static final Block STRIPPED_STARLIGHT_MANGROVE_WOOD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "stripped_starlight_mangrove_wood",
            () -> new SLFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.COLOR_RED)));
    public static final Block STARLIGHT_MANGROVE_DOOR = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_door",
            () -> new SLDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE_SET));
    public static final Block STARLIGHT_MANGROVE_TRAPDOOR = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_trapdoor",
            () -> new SLTrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE_SET));
    public static final Block STARLIGHT_MANGROVE_PRESSURE_PLATE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_pressure_plate",
            () -> new SLPressurePlateBlock(SLPressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE_SET));
    public static final Block STARLIGHT_MANGROVE_BUTTON = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_button",
            () -> new SLButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE_SET, 30, true));
    public static final Block STARLIGHT_MANGROVE_FENCE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_RED)));
    public static final Block STARLIGHT_MANGROVE_FENCE_GATE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE));
    public static final Block STARLIGHT_MANGROVE_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_RED)));
    public static final Block STARLIGHT_MANGROVE_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_stairs",
            () -> new StairBlock(() -> STARLIGHT_MANGROVE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).mapColor(MapColor.COLOR_RED)));
    public static final Block STARLIGHT_MANGROVE_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_sign",
            () -> new SLStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE));
    public static final Block STARLIGHT_MANGROVE_WALL_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_wall_sign",
            () -> new SLWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE));
    public static final Block STARLIGHT_MANGROVE_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_hanging_sign",
            () -> new SLCeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE));
    public static final Block STARLIGHT_MANGROVE_WALL_HANGING_SIGN = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_wall_hanging_sign",
            () -> new SLWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(MapColor.COLOR_RED), SLWoodTypes.STARLIGHT_MANGROVE));
    public static final Block STARLIGHT_MANGROVE_SAPLING = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_sapling") SaplingBlock(new StarlightMangroveTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_GREEN)));
    public static final Block POTTED_STARLIGHT_MANGROVE_SAPLING = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "potted_starlight_mangrove_sapling") FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), STARLIGHT_MANGROVE_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY).mapColor(MapColor.COLOR_GREEN)));
    public static final Block STARLIGHT_MANGROVE_ROOTS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_mangrove_roots") MangroveRootsBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_ROOTS)));
    public static final Block MUDDY_STARLIGHT_MANGROVE_ROOTS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "muddy_starlight_mangrove_roots") RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.MUDDY_MANGROVE_ROOTS)));

    //grimstone
    public static final Block GRIMSTONE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "grimstone") Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Block GRIMSTONE_BRICKS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "grimstone_bricks") Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final Block GRIMSTONE_BRICK_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "grimstone_brick_slab") SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));
    public static final Block GRIMSTONE_BRICK_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "grimstone_brick_stairs") StairBlock(() -> BlockInit.GRIMSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final Block GRIMSTONE_BRICK_WALL = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "grimstone_brick_wall") WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));
    public static final Block POLISHED_GRIMSTONE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_grimstone") Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final Block POLISHED_GRIMSTONE_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_grimstone_slab") SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));
    public static final Block POLISHED_GRIMSTONE_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_grimstone_stairs") StairBlock(() -> BlockInit.POLISHED_GRIMSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final Block POLISHED_GRIMSTONE_WALL = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_grimstone_wall") WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));
    public static final Block CHISELED_GRIMSTONE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "chiseled_grimstone") Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    //voidstone
    public static final Block VOIDSTONE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "voidstone") Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final Block VOIDSTONE_BRICKS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "voidstone_bricks") Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final Block VOIDSTONE_BRICK_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "voidstone_brick_slab") SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));
    public static final Block VOIDSTONE_BRICK_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "voidstone_brick_stairs") StairBlock(() -> BlockInit.VOIDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final Block VOIDSTONE_BRICK_WALL = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "voidstone_brick_wall") WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));
    public static final Block POLISHED_VOIDSTONE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_voidstone") Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final Block POLISHED_VOIDSTONE_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_voidstone_slab") SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)));
    public static final Block POLISHED_VOIDSTONE_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_voidstone_stairs") StairBlock(() -> BlockInit.POLISHED_VOIDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_STAIRS)));
    public static final Block POLISHED_VOIDSTONE_WALL = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "polished_voidstone_wall") WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));
    public static final Block CHISELED_VOIDSTONE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "chiseled_voidstone") Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).lightLevel((state) -> {return 15;})));

    //mud
    public static final Block NIGHTSHADE_MUD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_mud") MudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
    public static final Block GLOWING_NIGHTSHADE_MUD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_nightshade_mud") MudBlock(BlockBehaviour.Properties.copy(Blocks.MUD).lightLevel((state) -> {return 15;})));
    public static final Block PACKED_NIGHTSHADE_MUD = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "packed_nightshade_mud") Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD)));
    public static final Block NIGHTSHADE_MUD_BRICKS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_mud_bricks") Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final Block NIGHTSHADE_MUD_BRICK_SLAB = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_mud_brick_slab") SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_SLAB)));
    public static final Block NIGHTSHADE_MUD_BRICK_STAIRS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_mud_brick_stairs") StairBlock(() -> NIGHTSHADE_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_STAIRS)));
    public static final Block NIGHTSHADE_MUD_BRICK_WALL = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_mud_brick_wall") WallBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICK_WALL)));

    public static final Block STARLIGHT_FLOWER = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_flower") FlowerBlock(() -> MobEffects.DAMAGE_RESISTANCE, 10, BlockBehaviour.Properties.copy(Blocks.POPPY).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })));
    public static final Block POTTED_STARLIGHT_FLOWER = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "potted_starlight_flower") FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), STARLIGHT_FLOWER, BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })));
    public static final Block NIGHT_SPROUTS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "night_sprouts") BushBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_BLUE)) {
        @Override
        public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
            return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        }
    });
    public static final Block SMALL_NIGHT_SPROUTS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "small_night_sprouts") BushBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_BLUE)) {
        @Override
        public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
            return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        }
    });
    public static final Block GLOWING_NIGHT_SPROUTS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_night_sprouts") BushBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })) {
        @Override
        public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
            return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        }
    });
    public static final Block SMALL_GLOWING_NIGHT_SPROUTS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "small_glowing_night_sprouts") BushBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })) {
        @Override
        public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
            return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        }
    });
    public static final Block LUNAR_GRASS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_grass") TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_BLUE)) {
        @Override
        public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
            return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        }
    });
    public static final Block GLOWING_LUNAR_GRASS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_lunar_grass") TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })) {
        @Override
        public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
            return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        }
    });
    public static final Block CRESCENT_GRASS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "crescent_grass") TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_BLUE)));
    public static final Block GLOWING_CRESCENT_GRASS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_crescent_grass") TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })));
    public static final Block PARASOL_GRASS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "parasol_grass") TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_BLUE)));
    public static final Block GLOWING_PARASOL_GRASS = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_parasol_grass") TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.FERN).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })));
    public static final Block LUNAR_REED = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_reed") DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).mapColor(MapColor.COLOR_BLUE).lightLevel((state) -> {
        return 15;
    })));
    public static final Block GLOWING_MUSHROOM = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_mushroom") MushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(BlockInit::always).lightLevel((state) -> {
        return 15;
    }), ConfiguredFeatureInit.HUGE_GLOWING_MUSHROOM_KEY));
    public static final Block GLOWING_MUSHROOM_BLOCK = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "glowing_mushroom_block") HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(0.2F).sound(SoundType.WOOD).lightLevel((state) -> {
        return 15;
    })));
    public static final Block NIGHTSHADE_GRASS_BLOCK = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_grass_block") NightshadeGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block NIGHTSHADE_DIRT = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "nightshade_dirt") Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final Block SWAMP_SILVER_ORE = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "swamp_silver_ore") DropExperienceBlock(BlockBehaviour.Properties.of().strength(3.0F, 3.0F), UniformInt.of(3, 7)));
    public static final Block SWAMP_SILVER_BLOCK = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "swamp_silver_block") Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.STONE).strength(5.0F, 3.5F).sound(SoundType.STONE)));
    public static final Block ENERGY_BLOCK = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "energy_block") EnergyBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public static final Block STARLIGHT_GOLEM_SPAWNER = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_golem_spawner") StarlightGolemSpawnerBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public static final Block LUNAR_MONSTROSITY_SPAWNER = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "lunar_monstrosity_spawner") LunarMonstrositySpawnerBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public static final Block STARLIGHT_PORTAL = Registry.register(Registries.BLOCK,new Identifier(EternalStarlight.MODID, "starlight_portal", SLPortalBlock::new);
    private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
        return true;
    }
}
