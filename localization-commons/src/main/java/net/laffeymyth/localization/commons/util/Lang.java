package net.laffeymyth.localization.commons.util;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.laffeymyth.localization.commons.service.LocalizationService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Lang<T> {
    private final LocalizationService<T> service;
    private final String language;

    private static final Map<String, Lang<?>> CACHE = new ConcurrentHashMap<>();

    private Lang(LocalizationService<T> service, String language) {
        this.service = service;
        this.language = language;
    }

    @SuppressWarnings("unchecked")
    public static <T> Lang<T> of(LocalizationService<T> service, String language) {
        String key = service.getClass().getName() + ":" + language;
        return (Lang<T>) CACHE.computeIfAbsent(key, k -> new Lang<>(service, language));
    }

    @SuppressWarnings("unchecked")
    public static <T> Lang<T> of(Class<LocalizationService<T>> serviceClass, String language) {
        String key = serviceClass.getName() + ":" + language;
        return (Lang<T>) CACHE.computeIfAbsent(key, k -> {
            try {
                LocalizationService<T> service = serviceClass.getDeclaredConstructor().newInstance();
                return new Lang<>(service, language);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create instance of LocalizationService", e);
            }
        });
    }

    public T getMessage(String messageKey, TagResolver... tagResolvers) {
        return service.getMessage(messageKey, language, tagResolvers);
    }

    public List<T> getMessageList(String messageListKey, TagResolver... tagResolvers) {
        return service.getMessageList(messageListKey, language, tagResolvers);
    }

    public T getWord(String key, int number) {
        return service.getWord(key, number, language);
    }
}
