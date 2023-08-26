package com.umterrick.weatherbot.botapi;

import com.umterrick.weatherbot.botapi.BotStateContext;
import com.umterrick.weatherbot.db.models.TelegramUser;
import com.umterrick.weatherbot.db.repositories.UserRepository;
import com.umterrick.weatherbot.enums.BotCommands;
import com.umterrick.weatherbot.enums.BotState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/*
 * This class is a facade for the bot.
 * First to handle message
 * Send messege to choose handler
 */
@Component
@Slf4j
public class BotFacade {
    private BotStateContext botStateContext;
    private final UserRepository userRepository;


    public BotFacade(BotStateContext botStateContext,
                     UserRepository userRepository) {
        this.botStateContext = botStateContext;
        this.userRepository = userRepository;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
        }
//        if (update.hasCallbackQuery()) {
//            CallbackQuery callbackQuery = update.getCallbackQuery();
//            return handleCallbackQuery(callbackQuery);
//        }
        return replyMessage;
    }


    private SendMessage handleInputMessage(Message message) {
        String inputMessageText = message.getText();
        long chatId = message.getChatId();
        TelegramUser user = userRepository.findByChatId(chatId);
        if (user == null) {
            user = new TelegramUser(message.getFrom().getUserName(), chatId);
            userRepository.save(user);
        }
        BotState botState;
        SendMessage replyMessage;
        if (inputMessageText.startsWith("/")) {
            String commandInMessage = inputMessageText.substring(1).toUpperCase();
            BotCommands botCommand = BotCommands.valueOf(commandInMessage);
            botState = switch (botCommand) {
                case START -> BotState.START;
                case HELP -> BotState.SHOW_HELP_MENU;
                default -> user.getState();
            };
        } else {
            botState = user.getState();
        }
        user.setState(botState);
        replyMessage = botStateContext.processInputMessage(botState, message);
        return replyMessage;
    }

    //method to handle callback from main menu keyboard
//    private BotApiMethod<?> handleCallbackQuery(CallbackQuery buttonQuery) {
//        long chatId = buttonQuery.getMessage().getChatId();
//        BotState userBotState = userRepository.findBotStateByChatId(chatId);
//        return botStateContext.processCallbackQuery(userBotState, buttonQuery);
//    }

    //method to handle reply message from main menu keyboard
//    private SendMessage handleReplyMessage(BotState botState, Message message) {
//        SendMessage replyMessage = new SendMessage();
//        replyMessage.setChatId(message.getChatId());
//        replyMessage.setText(botStateContext.processReplyMessage(botState, message));
//        return botStateContext.processReplyMessage(botState, message);
//    }

}

