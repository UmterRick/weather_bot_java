package com.umterrick.weatherbot.botapi.handlers.comands;

import com.umterrick.weatherbot.db.models.City;
import com.umterrick.weatherbot.db.models.TelegramUser;
import com.umterrick.weatherbot.db.repositories.UserRepository;
import com.umterrick.weatherbot.enums.BotState;
import com.umterrick.weatherbot.service.MainMenuKeyboardService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SaveMainCityCommand implements InputMessageHandler {
    private UserRepository userRepository;
    private MainMenuKeyboardService mainMenuKeyboardService;
//    private SearchCity searchcity;   // search and validate city

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        long userId = message.getFrom().getId();
        City city = new City();
        String messageText = message.getText();

        TelegramUser user = userRepository.findByChatId(chatId);
        if (user != null) { // search city
            city.setName(messageText);
            user.setMainCity(city);
            user.setState(BotState.SHOW_MAIN_MENU);
            userRepository.save(user);
        }

            user.setState(BotState.SHOW_MAIN_MENU);

            userRepository.save(user);

        return mainMenuKeyboardService.getMainMenuMessage(chatId, "Місто збережене. Скористайтесь кнопками головного меню.");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SAVE_MAIN_CITY;
    }
}
