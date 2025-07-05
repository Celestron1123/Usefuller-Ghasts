package me.elijah.usefuller_ghasts;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;

import java.util.List;
import java.util.function.Consumer;

import static me.elijah.usefuller_ghasts.GhastDataTracker.FIRING;
import static me.elijah.usefuller_ghasts.GhastDataTracker.MOUTH_TIMER;

// Reference:
// import net.minecraft.entity.mob.GhastEntity;

public class GhastStickItem extends Item {
    public GhastStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> tooltip, TooltipType type) {
        tooltip.accept(Text.translatable("item.usefuller_ghasts.ghast_stick.tooltip").formatted(Formatting.DARK_RED));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient)
            return ActionResult.PASS;

        if (player.getItemCooldownManager().isCoolingDown(stack)) {
            return ActionResult.FAIL;
        }

        if (!(player.getVehicle() instanceof HappyGhastEntity ghast)) {
            return ActionResult.FAIL;
        }

        ghast.getDataTracker().set(FIRING, true);
        ghast.getDataTracker().set(MOUTH_TIMER, 25);
        HitResult hit = player.raycast(100, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK || hit.getType() == HitResult.Type.ENTITY) {
            world.syncWorldEvent(null, 1015, ghast.getBlockPos(), 0);
            world.syncWorldEvent(null, 1016, ghast.getBlockPos(), 0);

            Vec3d direction = ghast.getRotationVec(1.0F);
            double mouthOffset = -2.0;
            double f = hit.getPos().getX() - (ghast.getX() + direction.x * (double) 4.0F);
            double g = hit.getPos().getY() - ((double) 0.5F + ghast.getBodyY(0.5F) + mouthOffset);
            double h = hit.getPos().getZ() - (ghast.getZ() + direction.z * (double) 4.0F);
            Vec3d goodVec = new Vec3d(f, g, h);

            FireballEntity fireball = new FireballEntity(world, ghast, goodVec.normalize(), 1);
            fireball.setPosition(ghast.getX() + direction.x * (double) 4.0F, ghast.getBodyY(0.5F) + (double) 0.5F + mouthOffset, ghast.getZ() + direction.z * (double) 4.0F);
            world.spawnEntity(fireball);

            stack.damage(1, player, hand);

            player.getItemCooldownManager().set(stack, 60);
        } else {
            world.syncWorldEvent(null, 1015, ghast.getBlockPos(), 0);
            world.syncWorldEvent(null, 1016, ghast.getBlockPos(), 0);

            Vec3d direction = player.getRotationVec(1.0F);

            FireballEntity fireball = new FireballEntity(world, ghast, direction.normalize(), 1);
            fireball.setPosition(ghast.getX() + direction.x * (double) 4.0F, ghast.getBodyY(0.5F) + (double) 0.5F, ghast.getZ() + direction.z * (double) 4.0F);
            world.spawnEntity(fireball);

            stack.damage(1, player, hand);

            player.getItemCooldownManager().set(stack, 60);
        }
        return ActionResult.SUCCESS;
    }
}
