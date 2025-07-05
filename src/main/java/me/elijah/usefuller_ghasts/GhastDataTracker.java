package me.elijah.usefuller_ghasts;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.HappyGhastEntity;

public class GhastDataTracker {
    public static final TrackedData<Boolean> FIRING = DataTracker.registerData(HappyGhastEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Integer> MOUTH_TIMER = DataTracker.registerData(HappyGhastEntity.class, TrackedDataHandlerRegistry.INTEGER);
}
