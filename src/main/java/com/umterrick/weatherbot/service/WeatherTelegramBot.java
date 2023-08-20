package com.umterrick.weatherbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class WeatherTelegramBot extends TelegramLongPollingBot {
    @Value("${bot.Name")
    String BOT_NAME;

    public WeatherTelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    private void startCommandResponse(long chatId, String userName) {
        String responseText = "Hello Dear, " + userName;
        sendMessage(chatId, responseText);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);

        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }

    }
}
