import lombok.extern.slf4j.Slf4j;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.laffeymyth.localization.commons.service.ComponentLocalizationService;
import net.laffeymyth.localization.commons.service.LocalizationMessageSource;
import net.laffeymyth.localization.commons.service.MessageParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

@Slf4j
public class MessageParserTests {
    private final PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();
    private final ComponentLocalizationService localizationService = ComponentLocalizationService.lang();

    @BeforeAll
    public static void initLocalization() throws FileNotFoundException {
        LocalizationMessageSource ru = new LocalizationMessageSource();
        LocalizationMessageSource en = new LocalizationMessageSource();

        MessageParser messageParser = new MessageParser();
        messageParser.parse(ru, getReaderByFileName("test_messages_ru.json"));
        messageParser.parse(en, getReaderByFileName("test_messages_en.json"));

        ComponentLocalizationService.lang().getLanguageMap().put(Language.RUSSIAN.getShortName(), ru);
        ComponentLocalizationService.lang().getLanguageMap().put(Language.ENGLISH.getShortName(), en);
    }

    @Test
    public void message() {
        var message = localizationService.getMessage("welcome_message", Language.RUSSIAN.getShortName());
        log.info(plainTextComponentSerializer.serialize(message));
    }

    @Test
    public void messageList() {
        var messageList = localizationService.getMessageList("welcome_message_list", Language.ENGLISH.getShortName());
        messageList.forEach(textComponent -> log.info(plainTextComponentSerializer.serialize(textComponent)));
    }

    private static FileReader getReaderByFileName(String fileName) throws FileNotFoundException {
        File file = new File(Objects.requireNonNull(
                MessageParserTests.class.getClassLoader().getResource(fileName)).getFile()
        );

        return new FileReader(file);
    }
}
