package com.umterrick.weatherbot.botapi.handlers.comandHendlers;

import com.umterrick.weatherbot.botapi.handlers.InputMessageHandler;
import com.umterrick.weatherbot.db.models.TelegramUser;
import com.umterrick.weatherbot.db.repositories.UserRepository;
import com.umterrick.weatherbot.enums.BotState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class HandleStartCommand implements InputMessageHandler {
    private final UserRepository userRepository;

    @Autowired
    public HandleStartCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        long userId = message.getFrom().getId();

        TelegramUser user = userRepository.findByChatId(userId);

        String replyText;
        if (user != null) {
            replyText = "З поверненням, " + user.getUsername() + "!";
        } else {
            String username = message.getFrom().getUserName();
            user = new TelegramUser(username, chatId);
            userRepository.save(user);
            replyText = "Привіт, " + username + "! Ласкаво прошу, я бот погоди.\n" +
                    "Напишіть своє основне місто, для якого ви хотіли б отримувати щоденний прогноз";
        }

        user.setState(BotState.ASK_MAIN_CITY);
        userRepository.save(user);

        return createSendMessage(chatId, replyText);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }
}

