package com.umterrick.weatherbot.botapi.handlers.comandHendlers;

import com.umterrick.weatherbot.db.models.TelegramUser;
import com.umterrick.weatherbot.enums.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {
    BotState getCommandName();

    SendMessage handle(Message message, TelegramUser user);
}
