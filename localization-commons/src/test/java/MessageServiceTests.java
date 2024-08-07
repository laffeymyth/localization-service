import lombok.extern.slf4j.Slf4j;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.laffeymyth.localization.commons.service.ComponentLocalizationService;
import net.laffeymyth.localization.commons.service.LocalizationMessageSource;
import org.junit.jupiter.api.Test;

@Slf4j
public class MessageServiceTests {
    private final PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();

    @Test
    public void test() {
        var localizationMessageSource = new LocalizationMessageSource();

        localizationMessageSource.putMessage("player_join", "Игрок <player> зашёл на сервер");

        var componentLocalizationService = new ComponentLocalizationService();
        componentLocalizationService.getLanguageMap().put(Language.RUSSIAN.getShortName(), localizationMessageSource);

        var message = componentLocalizationService.getMessage("player_join", Language.RUSSIAN.getShortName(), TagResolver.builder()
                .resolver(TagResolver.resolver("player", (argumentQueue, context) -> Tag.inserting(Component.text("LaffeyMyth"))))
                .build());

        log.info(plainTextComponentSerializer.serialize(message));
    }
}
