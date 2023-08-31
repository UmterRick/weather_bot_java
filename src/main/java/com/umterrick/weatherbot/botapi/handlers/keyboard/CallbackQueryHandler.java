package com.umterrick.weatherbot.botapi.handlers.keyboard;

import com.umterrick.weatherbot.botapi.handlers.comands.InputMessageHandler;
import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CallbackQueryHandler implements InputMessageHandler {
    @Override
    public SendMessage handle(Message message) {
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.TAKE_FORECAST;
    }
}
