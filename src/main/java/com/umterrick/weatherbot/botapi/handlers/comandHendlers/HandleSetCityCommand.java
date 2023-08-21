package com.umterrick.weatherbot.botapi.handlers.comandHendlers;

import com.umterrick.weatherbot.botapi.handlers.InputMessageHandler;
import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HandleSetCityCommand implements InputMessageHandler {

    @Override
    public SendMessage handle(Message message) {
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return null;
    }
}
