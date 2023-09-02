package com.umterrick.weatherbot.botapi;

import com.umterrick.weatherbot.botapi.handlers.comands.InputMessageHandler;
import com.umterrick.weatherbot.enums.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class is used choose handler depending on the state of the bot
 */
@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    //search for message handlers in the constructor
    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    //handle message from user
    public SendMessage processInputMessage(BotState currentState, Message message) {

        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    //handle callback query from inline keyboard
//    public SendMessage processCallbackQuery(BotState currentState, CallbackQuery callbackQuery) {
//        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
//        return currentMessageHandler.handle(callbackQuery);
//    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (BotState.START == currentState) {
            return messageHandlers.get(BotState.START);
        }
        if (BotState.HELP == currentState) {
            return messageHandlers.get(BotState.HELP);
        }
        return messageHandlers.get(currentState);
    }

}


