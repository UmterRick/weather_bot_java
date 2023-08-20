package com.umterrick.weatherbot.botapi.handlers;

import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


public interface InputMessageHandler {
    SendMessage handle(Message message);

    BotState getHandlerName();
}
