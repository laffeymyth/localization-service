package net.laffeymyth.localization.bungee;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.kyori.adventure.util.Services;
import net.laffeymyth.localization.commons.service.ComponentLocalizationService;
import net.laffeymyth.localization.commons.service.LocalizationMessageSource;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;
import java.util.Map;
import java.util.Optional;

class BungeeLocalizationServiceImpl implements BungeeLocalizationService {
    private final ComponentLocalizationService componentLocalizationServiceImpl = ComponentLocalizationService.lang();
    private final BungeeComponentSerializer serializer = BungeeComponentSerializer.get();

    @Override
    public BaseComponent[] getMessage(String messageKey, String language, TagResolver... tagResolvers) {
        return serializer.serialize(
                componentLocalizationServiceImpl.getMessage(messageKey, language, tagResolvers)
        );
    }

    @Override
    public List<BaseComponent[]> getMessageList(String messageListKey, String language, TagResolver... tagResolvers) {
        return componentLocalizationServiceImpl.getMessageList(messageListKey, language, tagResolvers)
                .stream()
                .map(serializer::serialize)
                .toList();
    }

    @Override
    public BaseComponent[] getWord(String key, int number, String language) {
        return serializer.serialize(componentLocalizationServiceImpl.getWord(key, number, language));
    }

    @Override
    public Map<String, LocalizationMessageSource> getLanguageMap() {
        return componentLocalizationServiceImpl.getLanguageMap();
    }

    @SuppressWarnings("all")
    private static final Optional<Provider> SERVICE = Services.service(Provider.class);

    static final class Instances {
        static final BungeeLocalizationService INSTANCE = SERVICE
                .map(Provider::lang)
                .orElseGet(BungeeLocalizationServiceImpl::new);
    }
}
