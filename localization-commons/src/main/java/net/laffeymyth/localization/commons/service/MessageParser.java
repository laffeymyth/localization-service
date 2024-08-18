package net.laffeymyth.localization.commons.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.Reader;

public class MessageParser {
    public void parse(LocalizationMessageSource localizationMessageSource, Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        JsonElement jsonElement = JsonParser.parseReader(jsonReader);

        parseNode(localizationMessageSource, jsonElement, "");
    }

    public void parseNode(LocalizationMessageSource localizationMessageSource, JsonElement jsonElement, String key) {
        if (jsonElement.isJsonPrimitive()) {
            localizationMessageSource.putMessage(key, jsonElement.getAsString());
        }

        if (jsonElement.isJsonArray()) {
            localizationMessageSource.putMessageList(key, jsonElement.getAsJsonArray()
                    .asList()
                    .stream()
                    .map(JsonElement::getAsString)
                    .toList());
        }

        if (jsonElement.isJsonObject()) {
            jsonElement.getAsJsonObject().entrySet().forEach(stringJsonElementEntry -> {
                JsonElement value = stringJsonElementEntry.getValue();

                String newKey;
                if (key.isEmpty()) {
                    newKey = key + stringJsonElementEntry.getKey();
                } else {
                    newKey = key + "_" + stringJsonElementEntry.getKey();
                }

                parseNode(localizationMessageSource, value, newKey);
            });
        }
    }
}
