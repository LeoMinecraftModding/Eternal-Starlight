package cn.leolezury.eternalstarlight.block;

import cn.leolezury.eternalstarlight.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Mth;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public interface BerriesVines {
    VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    BooleanProperty BERRIES = Properties.BERRIES;

    static ActionResult use(BlockState blockState, World world, BlockPos pos) {
        if (blockState.getProperties(BERRIES)) {
            Block.popResource(world, pos, new ItemStack(ItemInit.LUNAR_BERRIES.get(), 1));
            float f = Mth.randomBetween(world.random, 0.8F, 1.2F);
            world.playSound((Player)null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            world.setBlock(pos, blockState.setValue(BERRIES, Boolean.valueOf(false)), 2);
            return ActionResult.sidedSuccess(world.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    static boolean hasLunarBerries(BlockState p_152952_) {
        return p_152952_.hasProperty(BERRIES) && p_152952_.getValue(BERRIES);
    }

    static ToIntFunction<BlockState> emission(int p_181218_) {
        return (p_181216_) -> {
            return p_181216_.getValue(BlockStateProperties.BERRIES) ? p_181218_ : 0;
        };
    }
}
