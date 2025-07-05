package me.elijah.usefuller_ghasts.mixin;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.elijah.usefuller_ghasts.GhastDataTracker.FIRING;
import static me.elijah.usefuller_ghasts.GhastDataTracker.MOUTH_TIMER;

@Mixin(HappyGhastEntity.class)
public class MixinHappyGhastEntity {

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void fireTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(FIRING, false);
        builder.add(MOUTH_TIMER, 0);
    }
}
