package cn.leolezury.eternalstarlight.world.feature.tree;

import cn.leolezury.eternalstarlight.init.PlacerInit;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.function.BiFunction;

public class LeafSpheroidFoliagePlacer extends FoliagePlacer {
    public static final Codec<LeafSpheroidFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68380_) -> {
        return foliagePlacerParts(p_68380_).apply(p_68380_, LeafSpheroidFoliagePlacer::new);
    });

    private final float horizontalRadius;
    public static final BiFunction<LevelSimulatedReader, BlockPos, Boolean> VALID_TREE_POS = TreeFeature::validTreePos;

    public LeafSpheroidFoliagePlacer(IntProvider horizontalRadius, IntProvider yOffset) {
        super(horizontalRadius, yOffset);

        this.horizontalRadius = (horizontalRadius.getMaxValue() + horizontalRadius.getMinValue()) / 2F;
    }


    protected FoliagePlacerType<LeafSpheroidFoliagePlacer> type() {
        return PlacerInit.FOLIAGE_SPHEROID.get();
    }

    public static void placeProvidedBlock(LevelSimulatedReader world, FoliageSetter setter, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, BlockPos pos, BlockStateProvider config, RandomSource random) {
        if (((Boolean)predicate.apply(world, pos)).booleanValue())
            setter.set(pos, config.getState(random, pos));
    }

    public static void placeSpheroid(LevelSimulatedReader world, FoliageSetter setter, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, RandomSource random, BlockPos centerPos, float xzRadius, float yRadius, float verticalBias, BlockStateProvider config) {
        float xzRadiusSquared = xzRadius * xzRadius;
        float yRadiusSquared = yRadius * yRadius;
        float superRadiusSquared = xzRadiusSquared * yRadiusSquared;
        placeProvidedBlock(world, setter, predicate, centerPos, config, random);

        for (int y = 0; y <= yRadius; y++) {
            if (y <= yRadius) {

                placeProvidedBlock(world, setter, predicate, centerPos.offset(0, y, 0), config, random);
                placeProvidedBlock(world, setter, predicate, centerPos.offset(0, -y, 0), config, random);
            }
        }
        for (int x = 0; x <= xzRadius; x++) {
            for (int z = 1; z <= xzRadius; z++) {
                if ((x * x + z * z) <= xzRadiusSquared) {

                    placeProvidedBlock(world, setter, predicate, centerPos.offset(x, 0, z), config, random);
                    placeProvidedBlock(world, setter, predicate, centerPos.offset(-x, 0, -z), config, random);
                    placeProvidedBlock(world, setter, predicate, centerPos.offset(-z, 0, x), config, random);
                    placeProvidedBlock(world, setter, predicate, centerPos.offset(z, 0, -x), config, random);

                    for (int i = 1; i <= yRadius; i++) {
                        float xzSquare = (x * x + z * z) * yRadiusSquared;

                        if (xzSquare + (i - verticalBias) * (i - verticalBias) * xzRadiusSquared <= superRadiusSquared) {
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(x, i, z), config, random);
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(-x, i, -z), config, random);
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(-z, i, x), config, random);
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(z, i, -x), config, random);
                        }

                        if (xzSquare + (i + verticalBias) * (i + verticalBias) * xzRadiusSquared <= superRadiusSquared) {
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(x, -i, z), config, random);
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(-x, -i, -z), config, random);
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(-z, -i, x), config, random);
                            placeProvidedBlock(world, setter, predicate, centerPos.offset(z, -i, -x), config, random);
                        }
                    }
                }
            }
        }
    }

    protected void createFoliage(LevelSimulatedReader worldReader, FoliageSetter setter, RandomSource random, TreeConfiguration baseTreeFeatureConfig, int trunkHeight, FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos center = foliage.pos().above(offset);

        placeSpheroid(worldReader, setter, VALID_TREE_POS, random, center, foliage.radiusOffset() + this.horizontalRadius + random.nextInt(3 + 1), foliage.radiusOffset() + 1.5F + random.nextInt(1 + 1), -0.25F, baseTreeFeatureConfig.foliageProvider);

        for (int i = 0; i < 15; i++) {
            float randomYaw = random.nextFloat() * 6.2831855F;
            float randomPitch = random.nextFloat() * 2.0F - 1.0F;
            float yUnit = Mth.sqrt(1.0F - randomPitch * randomPitch);
            float xCircleOffset = yUnit * Mth.cos(randomYaw) * (this.horizontalRadius - 1.0F);
            float zCircleOffset = yUnit * Mth.sin(randomYaw) * (this.horizontalRadius - 1.0F);

            BlockPos placement = center.offset((int) (xCircleOffset + ((int) xCircleOffset >> 31)), (int) (randomPitch * (1.5F + 0.25F) + -0.25F), (int) (zCircleOffset + ((int) zCircleOffset >> 31)));

            placeLeafCluster(worldReader, setter, random, placement.immutable(), baseTreeFeatureConfig.foliageProvider);
        }
    }

    private static void placeLeafCluster(LevelSimulatedReader worldReader, FoliageSetter setter, RandomSource random, BlockPos pos, BlockStateProvider state) {
        placeProvidedBlock(worldReader, setter, VALID_TREE_POS, pos, state, random);
        placeProvidedBlock(worldReader, setter, VALID_TREE_POS, pos.east(), state, random);
        placeProvidedBlock(worldReader, setter, VALID_TREE_POS, pos.south(), state, random);
        placeProvidedBlock(worldReader, setter, VALID_TREE_POS, pos.offset(1, 0, 1), state, random);
    }

    public int foliageHeight(RandomSource random, int i, TreeConfiguration baseTreeFeatureConfig) {
        return 0;
    }


    protected boolean shouldSkipLocation(RandomSource random, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}


