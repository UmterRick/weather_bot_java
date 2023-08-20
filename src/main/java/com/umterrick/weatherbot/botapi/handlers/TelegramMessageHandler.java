package com.umterrick.weatherbot.botapi.handlers;

import com.umterrick.weatherbot.botapi.BotStateContext;
import com.umterrick.weatherbot.enums.BotState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class TelegramMessageHandler {
    private BotStateContext botStateContext;


    public TelegramMessageHandler(BotStateContext botStateContext) {
        this.botStateContext = botStateContext;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, (chatId:{}, text:{}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    public SendMessage handleInputMessage(Message message) {
        String inputMessage = message.getText();
        long userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMessage) {
            case "/start":
                botState = BotState.SHOW_MAIN_MENU;
                break;
            case "/help":
                botState = BotState.SHOW_HELP_MENU;
                break;
            default:
//                botState = userData.getUserState(userId);
                break;

        }
        return null;
    }
}
