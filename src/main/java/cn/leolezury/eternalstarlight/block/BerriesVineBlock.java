package cn.leolezury.eternalstarlight.block;

import cn.leolezury.eternalstarlight.init.BlockInit;
import cn.leolezury.eternalstarlight.init.ItemInit;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class BerriesVineBlock extends AbstractPlantStemBlock implements Fertilizable, BerriesVines {
    private static final float CHANCE_OF_BERRIES_ON_GROWTH = 0.11F;

    protected int getGrowthLength(Random p_220928_) {
        return 1;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return false;
    }

    protected boolean canGrowInto(BlockState p_152998_) {
        return p_152998_.isAir();
    }

    protected Block getBodyBlock() {
        return BlockInit.BERRIES_VINES_PLANT.get();
    }

    protected BlockState updateBodyAfterConvertedFromHead(BlockState p_152987_, BlockState p_152988_) {
        return p_152988_.setValue(BERRIES, p_152987_.getValue(BERRIES));
    }

    protected BlockState getGrowIntoState(BlockState p_220935_, RandomSource p_220936_) {
        return super.getGrowIntoState(p_220935_, p_220936_).setValue(BERRIES, Boolean.valueOf(p_220936_.nextFloat() < 0.11F));
    }

    public ItemStack getCloneItemStack(BlockGetter p_152966_, BlockPos p_152967_, BlockState p_152968_) {
        return new ItemStack(ItemInit.LUNAR_BERRIES.get());
    }

    public InteractionResult use(BlockState p_152980_, Level p_152981_, BlockPos p_152982_, Player p_152983_, InteractionHand p_152984_, BlockHitResult p_152985_) {
        return BerriesVines.use(p_152980_, p_152981_, p_152982_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152993_) {
        super.createBlockStateDefinition(p_152993_);
        p_152993_.add(BERRIES);
    }

    public boolean isValidBonemealTarget(BlockGetter p_152970_, BlockPos p_152971_, BlockState p_152972_, boolean p_152973_) {
        return !p_152972_.getValue(BERRIES);
    }

    public boolean isBonemealSuccess(Level p_220930_, RandomSource p_220931_, BlockPos p_220932_, BlockState p_220933_) {
        return true;
    }

    public void performBonemeal(ServerLevel p_220923_, RandomSource p_220924_, BlockPos p_220925_, BlockState p_220926_) {
        p_220923_.setBlock(p_220925_, p_220926_.setValue(BERRIES, Boolean.valueOf(true)), 2);
    }

    @Override
    protected int getGrowthLength(Random random) {
        super(random, Direction.DOWN, SHAPE, false, 0.1D);
        this.setDefaultState(this.stateManager.getDefaultState()/*.setValue(AGE, Integer.valueOf(0))*/.setValue(BERRIES, Boolean.valueOf(false)));
    }

    @Override
    protected Block getPlant() {
        return null;
    }
}
