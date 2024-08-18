package net.laffeymyth.localization.commons.service;

import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

public interface ComponentLocalizationService extends LocalizationService<TextComponent> {
    static @NotNull ComponentLocalizationService lang() {
        return ComponentLocalizationServiceImpl.Instances.INSTANCE;
    }

    interface Provider {
        @NotNull
        ComponentLocalizationService lang();
    }
}
