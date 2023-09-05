package com.umterrick.weatherbot.botapi.handlers.keyboard;

import com.umterrick.weatherbot.db.repositories.CityRepository;
import com.umterrick.weatherbot.enums.BotCallbackPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Objects;

@Slf4j
@Component
public class CityCallbackQueryHandler implements CallbackQueryHandler {
    private CityRepository cityRepository;


    @Override
    public SendMessage handle(CallbackQuery callbackQuery) {
        String[] parsedCallbackData = callbackQuery.getData().split("-");
        String callbackObjectId = null;

        String callbackPrefix = parsedCallbackData[0];
        String callbackAction = parsedCallbackData[1];

        if (parsedCallbackData.length == 3) {
             callbackObjectId = parsedCallbackData[2];
        }
        log.info("handle callback prefix: " + callbackPrefix);

        log.info("handle callback action: " + callbackAction);
        log.info("handle callback object Id: " + callbackObjectId);

        if(callbackAction.equals("weather")){
            System.out.println("Get weather");
        } else if (callbackAction.equals("forecast")) {
            System.out.println("Get forecast");

        } else if (callbackAction.equals("delete")) {
            System.out.println("Delete");

        } else if (callbackAction.equals("add")) {
            System.out.println("Add");
        }


        return new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Will be soon");


    }

    @Override
    public BotCallbackPrefix getHandlerName() {
        return BotCallbackPrefix.CITY;
    }
}
