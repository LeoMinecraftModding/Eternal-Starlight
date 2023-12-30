package cn.leolezury.eternalstarlight.common.mixins;

import cn.leolezury.eternalstarlight.common.client.model.animation.PlayerAnimator;
import cn.leolezury.eternalstarlight.common.init.ItemInit;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @SuppressWarnings({"ConstantConditions"})
    @Inject(method = "evaluateWhichHandsToRender", at = @At(value = "RETURN"), cancellable = true)
    private static void es_evaluateWhichHandsToRender(LocalPlayer player, CallbackInfoReturnable<ItemInHandRenderer.HandRenderSelection> cir) {
        ItemStack itemStack = player.getMainHandItem();
        ItemStack itemStack1 = player.getOffhandItem();
        boolean flag = itemStack.is(ItemInit.CRYSTAL_CROSSBOW.get()) || itemStack.is(ItemInit.MOONRING_BOW.get()) || itemStack.is(ItemInit.STARFALL_LONGBOW.get()) || itemStack.is(ItemInit.BOW_OF_BLOOD.get());
        boolean flag1 = itemStack1.is(ItemInit.CRYSTAL_CROSSBOW.get()) || itemStack1.is(ItemInit.MOONRING_BOW.get()) || itemStack1.is(ItemInit.STARFALL_LONGBOW.get()) || itemStack1.is(ItemInit.BOW_OF_BLOOD.get());
        if (flag || flag1) {
            if (player.isUsingItem()) {
                ItemStack itemStack2 = player.getUseItem();
                InteractionHand interactionhand = player.getUsedItemHand();
                if (itemStack2.is(ItemInit.CRYSTAL_CROSSBOW.get()) || itemStack2.is(ItemInit.MOONRING_BOW.get()) || itemStack2.is(ItemInit.STARFALL_LONGBOW.get()) || itemStack2.is(ItemInit.BOW_OF_BLOOD.get())) {
                    cir.setReturnValue(ItemInHandRenderer.HandRenderSelection.onlyForHand(interactionhand));
                }
            } else {
                cir.setReturnValue((itemStack.is(ItemInit.CRYSTAL_CROSSBOW.get()) && CrossbowItem.isCharged(itemStack)) ? ItemInHandRenderer.HandRenderSelection.RENDER_MAIN_HAND_ONLY : ItemInHandRenderer.HandRenderSelection.RENDER_BOTH_HANDS);
            }
        }
    }

    @Inject(method = "renderHandsWithItems", at = @At("HEAD"), cancellable = true)
    private void es_renderHandsWithItems(float f, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LocalPlayer localPlayer, int i, CallbackInfo ci) {
        for (Map.Entry<PlayerAnimator.AnimationTrigger, PlayerAnimator.AnimationStateFunction> entry : PlayerAnimator.ANIMATIONS.entrySet()) {
            if (entry.getKey().shouldPlay(localPlayer)) {
                PlayerAnimator.PlayerAnimationState state = entry.getValue().get(localPlayer);
                if (state.renderLeftArm() || state.renderRightArm()) {
                    ci.cancel();
                }
            }
        }
    }
}

