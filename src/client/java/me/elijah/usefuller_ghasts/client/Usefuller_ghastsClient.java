package me.elijah.usefuller_ghasts.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import me.elijah.usefuller_ghasts.client.renderer.HappyGhastRenderer;

public class Usefuller_ghastsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityType.HAPPY_GHAST, HappyGhastRenderer::new);
    }
}
