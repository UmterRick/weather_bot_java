package com.umterrick.weatherbot.botapi.handlers.keyboard;

import com.umterrick.weatherbot.enums.BotCallbackPrefix;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class CityCallbackQueryHandler implements CallbackQueryHandler {


    @Override
    public SendMessage handle(CallbackQuery callbackQuery) {
        callbackQuery.getData().split("-");
    }

    @Override
    public BotCallbackPrefix getHandlerName() {
        return BotCallbackPrefix.CITY;
    }
}
