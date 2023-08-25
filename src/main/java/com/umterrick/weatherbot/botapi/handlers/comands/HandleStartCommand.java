package com.umterrick.weatherbot.botapi.handlers.comands;

import com.umterrick.weatherbot.db.models.TelegramUser;
import com.umterrick.weatherbot.db.repositories.UserRepository;
import com.umterrick.weatherbot.enums.BotState;
import com.umterrick.weatherbot.service.MainMenuKeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class HandleStartCommand implements InputMessageHandler {
    private final UserRepository userRepository;
    private final MainMenuKeyboardService mainMenuKeyboardService;
    @Autowired
    public HandleStartCommand(UserRepository userRepository, MainMenuKeyboardService mainMenuKeyboardService) {
        this.userRepository = userRepository;
        this.mainMenuKeyboardService = mainMenuKeyboardService;
    }

    /*
    * Handle the start command
    * returns the main menu
     */
    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        long userId = message.getFrom().getId();

        TelegramUser user = userRepository.findByChatId(userId);

        String replyText;
        if (user.getMainCity() != null) {
            replyText = "З поверненням, " + user.getUsername() + "!";
            user.setState(BotState.SAVE_MAIN_CITY);
        } else {
            String username = message.getFrom().getUserName();
            user = new TelegramUser(username, chatId);
            userRepository.save(user);
            user.setState(BotState.SAVE_MAIN_CITY);
            replyText = "Привіт, " + username + "! Ласкаво прошу, я бот погоди.\n" +
                    "Введіть назву свого міста";
        }


        userRepository.save(user);

        return mainMenuKeyboardService.getMainMenuMessage(chatId, replyText);
    }


    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }
}

