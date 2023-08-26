package com.umterrick.weatherbot.botapi.handlers.comands;

import com.umterrick.weatherbot.db.models.telegram.City;
import com.umterrick.weatherbot.db.models.telegram.TelegramUser;
import com.umterrick.weatherbot.db.repositories.CityRepository;
import com.umterrick.weatherbot.db.repositories.UserRepository;
import com.umterrick.weatherbot.enums.BotState;
import com.umterrick.weatherbot.service.MainMenuKeyboardService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SaveMainCityHandler implements InputMessageHandler {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final MainMenuKeyboardService mainMenuKeyboardService;

    public SaveMainCityHandler(UserRepository userRepository, CityRepository cityRepository, MainMenuKeyboardService mainMenuKeyboardService) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.mainMenuKeyboardService = mainMenuKeyboardService;
    }
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
            cityRepository.save(city);
            user.setMainCity(city);
            user.setState(BotState.SHOW_MAIN_MENU);
            userRepository.save(user);
        }
        return mainMenuKeyboardService.getMainMenuMessage(chatId, "Місто збережене. Скористайтесь кнопками головного меню.");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SAVE_MAIN_CITY;
    }
}
