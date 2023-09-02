package com.umterrick.weatherbot.botapi.handlers.comands.weather.handlers;

import com.umterrick.weatherbot.botapi.handlers.comands.InputMessageHandler;
import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ForecastHandler implements InputMessageHandler {

    @Override
    public SendMessage handle(Message message) {
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.TAKE_FORECAST;
    }
}
