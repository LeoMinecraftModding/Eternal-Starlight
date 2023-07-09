package cn.leolezury.eternalstarlight.mixins;

import cn.leolezury.eternalstarlight.init.ItemInit;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HeldItemRenderer.class)
abstract class ItemInHandRendererMixin {
    @SuppressWarnings({"ConstantConditions"})
    @Inject(method = "getHandRenderType", at = @At(value = "RETURN"), cancellable = true)
    private static void getHandRenderType(ClientPlayerEntity player, CallbackInfoReturnable<HeldItemRenderer.HandRenderType> cir) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack1 = player.getOffHandStack();
        boolean flag = itemStack.isOf(ItemInit.CRYSTAL_CROSSBOW) || itemStack.isOf(ItemInit.LUNAR_MONSTROSITY_BOW);
        boolean flag1 = itemStack1.isOf(ItemInit.CRYSTAL_CROSSBOW) || itemStack1.isOf(ItemInit.LUNAR_MONSTROSITY_BOW);
        if (flag || flag1) {
            if (player.isUsingItem()) {
                ItemStack itemStack2 = player.getActiveItem();
                Hand hand = player.getActiveHand();
                if (itemStack2.isOf(ItemInit.CRYSTAL_CROSSBOW) || itemStack2.isOf(ItemInit.LUNAR_MONSTROSITY_BOW)) {
                    cir.setReturnValue(HeldItemRenderer.HandRenderType.shouldOnlyRender(hand));
                }
            } else {
                cir.setReturnValue((itemStack.isOf(ItemInit.CRYSTAL_CROSSBOW) && CrossbowItem.isCharged(itemStack)) ? HeldItemRenderer.HandRenderType.RENDER_MAIN_HAND_ONLY : HeldItemRenderer.HandRenderType.RENDER_BOTH_HANDS);
            }
        }
    }
}

