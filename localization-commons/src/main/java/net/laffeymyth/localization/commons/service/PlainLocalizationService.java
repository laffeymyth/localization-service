package net.laffeymyth.localization.commons.service;

import org.jetbrains.annotations.NotNull;

public interface PlainLocalizationService extends LocalizationService<String> {
    static @NotNull PlainLocalizationService lang() {
        return PlainLocalizationServiceImpl.Instances.INSTANCE;
    }

    interface Provider {
        @NotNull
        PlainLocalizationService lang();
    }
}
