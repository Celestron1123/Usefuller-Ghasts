package me.elijah.usefuller_ghasts.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.elijah.usefuller_ghasts.GhastDataTracker.FIRING;
import static me.elijah.usefuller_ghasts.GhastDataTracker.MOUTH_TIMER;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if ((Object) this instanceof HappyGhastEntity ghast) {
            if (ghast.getDataTracker().get(FIRING)) {
                int ticks = ghast.getDataTracker().get(MOUTH_TIMER);
                if (ticks > 0) {
                    ghast.getDataTracker().set(MOUTH_TIMER, --ticks);
                } else {
                    ghast.getDataTracker().set(FIRING, false);
                }
            }
        }
    }
}
