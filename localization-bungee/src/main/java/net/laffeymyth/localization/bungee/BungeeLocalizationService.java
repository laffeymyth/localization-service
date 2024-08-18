package net.laffeymyth.localization.bungee;

import net.laffeymyth.localization.commons.service.LocalizationService;
import net.md_5.bungee.api.chat.BaseComponent;
import org.jetbrains.annotations.NotNull;

public interface BungeeLocalizationService extends LocalizationService<BaseComponent[]> {
    static @NotNull BungeeLocalizationService lang() {
        return BungeeLocalizationServiceImpl.Instances.INSTANCE;
    }

    interface Provider {
        @NotNull
        BungeeLocalizationService lang();
    }
}
