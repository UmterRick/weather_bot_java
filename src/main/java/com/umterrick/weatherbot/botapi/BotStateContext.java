package com.umterrick.weatherbot.botapi;

import com.umterrick.weatherbot.botapi.handlers.InputMessageHandler;
import com.umterrick.weatherbot.enums.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }


    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (isCommandState(currentState)) {
            return messageHandlers.get(BotState.HANDLE_COMMAND);
        }
        return messageHandlers.get(currentState);
    }

    private boolean isCommandState(BotState currentState) {
        switch (currentState) {
            case START:
            case HELP:
                return true;

            default:
                return false;
        }
    }

}

