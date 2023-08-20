package com.umterrick.weatherbot.botapi.handlers;

import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HandleCommand implements InputMessageHandler {
    @Override
    public SendMessage handle(Message message) {


        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HANDLE_COMMAND;
    }
}
