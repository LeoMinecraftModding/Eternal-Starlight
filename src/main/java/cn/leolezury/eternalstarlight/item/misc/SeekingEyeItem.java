package cn.leolezury.eternalstarlight.item.misc;

import cn.leolezury.eternalstarlight.entity.misc.EyeOfSeeking;
import cn.leolezury.eternalstarlight.init.ItemInit;
import cn.leolezury.eternalstarlight.init.SoundEventInit;
import cn.leolezury.eternalstarlight.util.SLTags;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.structure.Structure;

public class SeekingEyeItem extends Item {
    public SeekingEyeItem(FabricItemSettings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (hand != Hand.MAIN_HAND) {
            return TypedActionResult.fail(itemStack);
        }
        player.setCurrentHand(hand);
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)world;
            TagKey<Structure> structureTagKey = SLTags.Structures.BOSS_STRUCTURES;
            if (player.getStackInHand(Hand.OFF_HAND).isOf(Items.REDSTONE)) {
                structureTagKey = SLTags.Structures.GOLEM_FORGE;
            }
            if (player.getStackInHand(Hand.OFF_HAND).isOf(ItemInit.RED_CRYSTAL_MOSS_CARPET) || player.getStackInHand(Hand.OFF_HAND).isOf(ItemInit.BLUE_CRYSTAL_MOSS_CARPET)) {
                structureTagKey = SLTags.Structures.MOSSY_MAZE;
            }
            // else if
            BlockPos blockPos = serverWorld.locateStructure(structureTagKey, player.getBlockPos(), 100, false);
            if (blockPos != null) {
                EyeOfSeeking eyeOfSeeking = new EyeOfSeeking(world, player.getX(), player.getBodyY(0.5D), player.getZ());
                eyeOfSeeking.setItem(itemStack);
                eyeOfSeeking.signalTo(blockPos);
                world.emitGameEvent(GameEvent.PROJECTILE_SHOOT, eyeOfSeeking.getPos(), GameEvent.Emitter.of(player));
                world.spawnEntity(eyeOfSeeking);

                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEventInit.SEEKING_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return TypedActionResult.success(itemStack);
            }
        }

        return TypedActionResult.consume(itemStack);
    }
}
