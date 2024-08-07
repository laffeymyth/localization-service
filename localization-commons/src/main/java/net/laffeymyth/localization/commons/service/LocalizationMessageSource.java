package net.laffeymyth.localization.commons.service;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class LocalizationMessageSource {
    private final Map<String, String> messageMap = new ConcurrentHashMap<>();
    private final Map<String, List<String>> messageListMap = new ConcurrentHashMap<>();

    public String getMessage(String messageKey) {
        String message = messageMap.get(messageKey);

        if (message == null){
            message = "<red>" + messageKey;
        }

        return message;
    }

    public List<String> getMessageList(String messageListKey) {
        List<String> messageList = messageListMap.get(messageListKey);

        if (messageList == null || messageList.isEmpty()) {
            messageList = List.of("<red>" + messageListKey);
        }

        return messageList;
    }

    public void putMessage(String messageKey, String message) {
        messageMap.put(messageKey, message);
    }

    public void putMessageList(String messageKey, List<String> messageList) {
        messageListMap.put(messageKey, messageList);
    }
}
