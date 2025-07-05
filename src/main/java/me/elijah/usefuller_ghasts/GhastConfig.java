package me.elijah.usefuller_ghasts;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "usefuller_ghasts")
@Config.Gui.Background("minecraft:textures/block/netherrack.png")
public class GhastConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(max = 100)
    public static int speedMultiplier = 20;

    public static double getSpeed(){
        return speedMultiplier / 100.0;
    }
}
