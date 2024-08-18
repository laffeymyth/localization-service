package net.laffeymyth.localization.commons.service;

import org.jetbrains.annotations.NotNull;

public interface LegacyLocalizationService extends LocalizationService<String> {
    static @NotNull LegacyLocalizationService lang() {
        return LegacyLocalizationServiceImpl.Instances.INSTANCE;
    }

    interface Provider {
        @NotNull
        LegacyLocalizationService lang();
    }
}
