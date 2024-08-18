package net.laffeymyth.localization.commons.service;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.util.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class PlainLocalizationServiceImpl implements PlainLocalizationService {
    private final ComponentLocalizationService componentLocalizationService = ComponentLocalizationService.lang();
    private final PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();

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
    private static final Optional<Provider> SERVICE = Services.service(Provider.class);

    static final class Instances {
        static final PlainLocalizationService INSTANCE = SERVICE
                .map(Provider::lang)
                .orElseGet(PlainLocalizationServiceImpl::new);
    }
}
