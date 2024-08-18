package net.laffeymyth.localization.commons.service;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.util.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class LegacyLocalizationServiceImpl implements LegacyLocalizationService {
    private final ComponentLocalizationService componentLocalizationService = ComponentLocalizationService.lang();
    private final LegacyComponentSerializer serializer = LegacyComponentSerializer.legacySection();

    @Override
    public String getMessage(String messageKey, String language, TagResolver... tagResolvers) {
        return serializer.serialize(
                componentLocalizationService.getMessage(messageKey, language, tagResolvers)
        );
    }

    @Override
    public List<String> getMessageList(String messageListKey, String language, TagResolver... tagResolvers) {
        return componentLocalizationService.getMessageList(messageListKey, language, tagResolvers)
                .stream()
                .map(serializer::serialize)
                .toList();
    }

    @Override
    public String getWord(String key, int number, String language) {
        return serializer.serialize(componentLocalizationService.getWord(key, number, language));
    }

    @Override
    public Map<String, LocalizationMessageSource> getLanguageMap() {
        return componentLocalizationService.getLanguageMap();
    }

    @SuppressWarnings("all")
    private static final Optional<LegacyLocalizationService.Provider> SERVICE = Services.service(LegacyLocalizationService.Provider.class);

    static final class Instances {
        static final LegacyLocalizationService INSTANCE = SERVICE
                .map(LegacyLocalizationService.Provider::lang)
                .orElseGet(LegacyLocalizationServiceImpl::new);
    }
}
