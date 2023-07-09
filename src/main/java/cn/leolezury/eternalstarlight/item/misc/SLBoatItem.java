package cn.leolezury.eternalstarlight.item.misc;

import cn.leolezury.eternalstarlight.entity.misc.SLBoat;
import cn.leolezury.eternalstarlight.entity.misc.SLChestBoat;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.function.Predicate;

public class SLBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::canHit);
    private final SLBoat.Type type;
    private final boolean chest;

    public SLBoatItem(boolean chest, SLBoat.Type type, FabricItemSettings settings) {
        super(settings);
        this.chest = chest;
        this.type = type;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        HitResult result = raycast(world, player, RaycastContext.FluidHandling.ANY);
        if (result.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemstack);
        }
        else {
            Vec3d vector3d = player.getRotationVec(1.0F);
            List<Entity> list = world.getOtherEntities(player, player.getBoundingBox().stretch(vector3d.multiply(5.0D)).expand(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3d vector3d1 = player.getCameraPosVec(1.0F);

                for(Entity entity : list) {
                    Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                    if (box.contains(vector3d1)) {
                        return TypedActionResult.pass(itemstack);
                    }
                }
            }

            if (result.getType() == HitResult.Type.BLOCK) {
                SLBoat boat = this.getBoat(world, result);
                boat.setSLBoatType(this.type);
                boat.setYaw(player.getYaw());
                if (!world.isSpaceEmpty(boat, boat.getBoundingBox())) {
                    return TypedActionResult.fail(itemstack);
                }
                else {
                    if (!world.isClient()) {
                        world.spawnEntity(boat);
                        world.emitGameEvent(player, GameEvent.ENTITY_PLACE, result.getPos());
                        if (!player.getAbilities().creativeMode) {
                            itemstack.decrement(1);
                        }
                    }

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemstack, world.isClient());
                }
            }
            else {
                return TypedActionResult.pass(itemstack);
            }
        }
    }

    private SLBoat getBoat(World world, HitResult result) {
        return this.chest ? new SLChestBoat(world, result.getPos().x, result.getPos().y, result.getPos().z) : new SLBoat(world, result.getPos().x, result.getPos().y, result.getPos().z);
    }
}
