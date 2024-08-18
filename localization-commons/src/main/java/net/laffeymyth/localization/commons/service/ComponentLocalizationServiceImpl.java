package net.laffeymyth.localization.commons.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.util.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class ComponentLocalizationServiceImpl implements ComponentLocalizationService {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    @Getter
    private final Map<String, LocalizationMessageSource> languageMap = new ConcurrentHashMap<>();

    @Override
    public TextComponent getMessage(String messageKey, String language, TagResolver... tagResolvers) {
        LocalizationMessageSource localizationMessageSource = languageMap.get(language);

        if (localizationMessageSource == null) {
            throw new RuntimeException("Источник сообщений язка" + language + " не найден!");
        }

        return Component.empty().append(miniMessage.deserialize(localizationMessageSource.getMessage(messageKey), tagResolvers));
    }

    @Override
    public List<TextComponent> getMessageList(String messageListKey, String language, TagResolver... tagResolvers) {
        LocalizationMessageSource localizationMessageSource = languageMap.get(language);

        if (localizationMessageSource == null) {
            throw new RuntimeException("Источник сообщений язка" + language + " не найден!");
        }

        localizationMessageSource.getMessageList(messageListKey);
        return localizationMessageSource.getMessageList(messageListKey)
                .stream().map(s -> Component.empty().append(miniMessage.deserialize(s, tagResolvers)))
                .toList();
    }

    @Override
    public TextComponent getWord(String key, int number, String language) {
        TextComponent word = getMessageList(key, language).get(0);

        if (number % 100 > 10 && number % 100 < 15) {
            return word.append(getMessageList(key, language).get(3));
        } else {
            return switch (number % 10) {
                case 1 -> word.append(getMessageList(key, language).get(1));
                case 2, 3, 4 -> word.append(getMessageList(key, language).get(2));
                default -> word.append(getMessageList(key, language).get(3));
            };
        }
    }

    @SuppressWarnings("all")
    private static final Optional<Provider> SERVICE = Services.service(Provider.class);

    static final class Instances {
        static final ComponentLocalizationService INSTANCE = SERVICE
                .map(Provider::lang)
                .orElseGet(ComponentLocalizationServiceImpl::new);
    }
}
