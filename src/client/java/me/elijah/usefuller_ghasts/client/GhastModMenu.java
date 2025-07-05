package me.elijah.usefuller_ghasts.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.elijah.usefuller_ghasts.GhastConfig;
import me.shedaniel.autoconfig.AutoConfig;

public class GhastModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(GhastConfig.class, parent).get();
    }
}

