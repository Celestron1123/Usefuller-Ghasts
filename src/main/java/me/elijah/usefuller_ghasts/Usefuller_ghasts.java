/**
 * Main initializer page for usefuller ghasts
 *
 * @author Elijah Potter
 * @date 7/1/2025
 */

package me.elijah.usefuller_ghasts;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

public class Usefuller_ghasts implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("usefuller_ghasts");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Usefuller Ghasts");
        GhastStickRegister.initialize();
        AutoConfig.register(GhastConfig.class, Toml4jConfigSerializer::new);
        Class<?> c = GhastDataTracker.class;
        LOGGER.info("GhastDataTracker loaded: {}", c.getName());
        LOGGER.info("Ghast tracker slot: {}", GhastDataTracker.FIRING);

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            double speedMul = GhastConfig.getSpeed();

            server.getWorlds().forEach(world -> {
                for (var entity : world.iterateEntities()) {
                    if (entity instanceof HappyGhastEntity ghast) {
                        var attr = ghast.getAttributeInstance(EntityAttributes.FLYING_SPEED);
                        if (attr != null) {
                            if (ghast.hasPlayerRider())
                                attr.setBaseValue(0.05 + (0.05 * speedMul));
                            else
                                attr.setBaseValue(0.05);
                        }
                    }
                }
            });
        });
    }
}
