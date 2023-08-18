package com.umterrick.weatherbot.service;

import com.umterrick.weatherbot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class WeatherTelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public WeatherTelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start":
                    startCommandResponse(chatId, update.getMessage().getChat().getUserName());
                    break;

                default: sendMessage(chatId, update.getMessage().getText());


            }
        }
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
