package net.laffeymyth.localization.bungee;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.laffeymyth.localization.commons.service.ComponentLocalizationService;
import net.laffeymyth.localization.commons.service.LocalizationService;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;

public class BungeeLocalizationService implements LocalizationService<BaseComponent[]> {
    private final ComponentLocalizationService componentLocalizationService = new ComponentLocalizationService();
    private final BungeeComponentSerializer bungeeComponentSerializer = BungeeComponentSerializer.get();

    @Override
    public BaseComponent[] getMessage(String messageKey, String language, TagResolver... tagResolvers) {
        return bungeeComponentSerializer.serialize(
                componentLocalizationService.getMessage(messageKey, language, tagResolvers)
        );
    }

    @Override
    public List<BaseComponent[]> getMessageList(String messageListKey, String language, TagResolver... tagResolvers) {
        return componentLocalizationService.getMessageList(messageListKey, language, tagResolvers)
                .stream()
                .map(bungeeComponentSerializer::serialize)
                .toList();
    }
}
