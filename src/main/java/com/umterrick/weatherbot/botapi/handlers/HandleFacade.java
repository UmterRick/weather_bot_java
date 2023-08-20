package com.umterrick.weatherbot.botapi.handlers;

import com.umterrick.weatherbot.enums.BotCommands;
import com.umterrick.weatherbot.enums.BotState;
import com.umterrick.weatherbot.botapi.BotStateContext;
import com.umterrick.weatherbot.db.models.TelegramUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HandleFacade {
    private BotStateContext botStateContext;
    private TelegramUser user;

    public HandleFacade(BotStateContext botStateContext, TelegramUser user) {
        this.botStateContext = botStateContext;
        this.user = user;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMessageText = message.getText();
        BotState botState;
        SendMessage replyMessage;

        String commandInMessage = inputMessageText.substring(1).toUpperCase();
        BotCommands botCommand = BotCommands.valueOf(commandInMessage);
        botState = switch (botCommand) {
            case START -> BotState.SHOW_MAIN_MENU;
            case HELP -> BotState.SHOW_HELP_MENU;
            default -> user.getState();
        };
        user.setState(botState);
        replyMessage = botStateContext.processInputMessage(botState, message);
        return replyMessage;


    }
}
