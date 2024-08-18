package net.laffeymyth.localization.commons.service;


import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.List;
import java.util.Map;

public interface LocalizationService<T> {
    T getMessage(String messageKey, String language, TagResolver... tagResolvers);

    List<T> getMessageList(String messageListKey, String language, TagResolver... tagResolvers);

    T getWord(String key, int number, String language);

    Map<String, LocalizationMessageSource> getLanguageMap();
}
