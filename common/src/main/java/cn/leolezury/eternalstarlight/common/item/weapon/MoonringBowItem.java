package cn.leolezury.eternalstarlight.common.item.weapon;

import cn.leolezury.eternalstarlight.common.entity.attack.LunarVine;
import cn.leolezury.eternalstarlight.common.registry.ESEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MoonringBowItem extends BowItem {
    public MoonringBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int n) {
        super.releaseUsing(itemStack, level, livingEntity, n);
        int k = this.getUseDuration(itemStack) - n;
        float powerForTime = getPowerForTime(k);
        if (!level.isClientSide && livingEntity instanceof Player && powerForTime == 1.0) {
            float x = -Mth.sin(livingEntity.getYRot() * ((float)Math.PI / 180F));
            float z = Mth.cos(livingEntity.getYRot() * ((float)Math.PI / 180F));
            for (int i = 0; i < 16; i++) {
                createVine((ServerLevel) level, livingEntity, livingEntity.getX() + x * i, livingEntity.getY(), livingEntity.getZ() + z * i);
            }
        }
    }

    private void createVine(ServerLevel level, LivingEntity owner, double x, double y, double z) {
        boolean canCreate = true;
        int blockY = 0;
        if (!level.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isAir()) {
            for (int i = (int) y; !level.getBlockState(new BlockPos((int) x, i, (int) z)).isAir(); i++) {
                if (i > level.getMaxBuildHeight()) {
                    canCreate = false;
                    break;
                }
                blockY = i;
            }
        } else {
            for (int i = (int) y; level.getBlockState(new BlockPos((int) x, i, (int) z)).isAir(); i--) {
                if (i < level.getMinBuildHeight()) {
                    canCreate = false;
                    break;
                }
                blockY = i + 1;
            }
        }
        if (canCreate) {
            LunarVine vine = ESEntities.LUNAR_VINE.get().create(level);
            vine.setPos(x, blockY, z);
            vine.setAttackMode(0);
            vine.setOwner(owner);
            level.addFreshEntity(vine);
        }
    }
}
