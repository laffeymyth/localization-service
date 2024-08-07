package net.laffeymyth.localization.commons.service;


import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.List;

public interface LocalizationService<T> {
    T getMessage(String messageKey, String language, TagResolver... tagResolvers);

    List<T> getMessageList(String messageListKey, String language, TagResolver... tagResolvers);
}
