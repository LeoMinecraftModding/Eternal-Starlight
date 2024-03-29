package cn.leolezury.eternalstarlight.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public abstract class ESFeature<FC extends FeatureConfiguration> extends Feature<FC> {
    public ESFeature(Codec<FC> codec) {
        super(codec);
    }

    protected boolean isValidBlock(BlockState state, List<TagKey<Block>> tags) {
        for (TagKey<Block> tag : tags) {
            if (state.is(tag)) {
                return true;
            }
        }
        return false;
    }

    protected boolean setBlockIfEmpty(WorldGenLevel level, BlockPos pos, BlockState state) {
        if (level.isEmptyBlock(pos)) {
            setBlock(level, pos, state);
            return true;
        }
        return false;
    }

    protected boolean setBlockIfEmpty(WorldGenLevel level, BlockPos pos, BlockState state, List<TagKey<Block>> ignored) {
        if (level.isEmptyBlock(pos) || isValidBlock(level.getBlockState(pos), ignored)) {
            setBlock(level, pos, state);
            return true;
        }
        return false;
    }

    protected void placeOnTop(WorldGenLevel worldGenLevel, BlockPos blockPos, BlockState state, List<TagKey<Block>> ignored) {
        BlockPos pos = blockPos;
        for(; (isValidBlock(worldGenLevel.getBlockState(pos), ignored) || worldGenLevel.isEmptyBlock(pos) || !worldGenLevel.getFluidState(pos).equals(Fluids.EMPTY.defaultFluidState())) && pos.getY() > worldGenLevel.getMinBuildHeight() + 2; pos = pos.below());
        pos = pos.above();
        if (worldGenLevel.getBlockState(pos).isAir()) {
            setBlock(worldGenLevel, pos, state);
        }
    }

    protected BlockPos getChunkCoordinate(BlockPos origin) {
        return new BlockPos((origin.getX() / 16) * 16, origin.getY(), (origin.getZ() / 16) * 16);
    }
}
