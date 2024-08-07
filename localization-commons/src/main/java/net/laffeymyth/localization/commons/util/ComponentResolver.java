package net.laffeymyth.localization.commons.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.TagPattern;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.function.BiFunction;

@UtilityClass
public class ComponentResolver {
    public TagResolver tag(@TagPattern String key, Component component) {
        return TagResolver.resolver(key, (argumentQueue, context) -> Tag.inserting(component));
    }

    public TagResolver tag(@TagPattern String key, BiFunction<ArgumentQueue, Context, Component> function) {
        return TagResolver.resolver(key, function.andThen(Tag::inserting));
    }
}
