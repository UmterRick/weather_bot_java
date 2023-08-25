package com.umterrick.weatherbot;

import com.umterrick.weatherbot.botapi.BotFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class WeatherTelegramBot extends TelegramLongPollingBot {

    private final BotFacade botFacade;
    @Value("${bot.Name")
    String botName;

    public WeatherTelegramBot(@Value("${bot.token}") String botToken, BotFacade botFacade) {
        super(botToken);
        this.botFacade = botFacade;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotApiMethod<?> replyMessage = botFacade.handleUpdate(update);
    }

//    private void startCommandResponse(long chatId, String userName) {
//        String responseText = "Hello Dear, " + userName;
//        sendMessage(chatId, responseText);
//    }
//    private void sendMessage(long chatId, String text) {
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(text);
//        botFacade.handleUpdate(message);
//        try {
//            execute(message);
//
//        } catch (TelegramApiException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
