package com.umterrick.weatherbot.botapi.handlers.comands;

import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/*
 * This class is used to handle the main menu of the bot.
 */
public class MainMenuHandler implements InputMessageHandler {

    @Override
    public SendMessage handle(Message message) {
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MAIN_MENU;
    }
}
