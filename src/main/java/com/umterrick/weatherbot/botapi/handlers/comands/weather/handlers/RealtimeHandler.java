package com.umterrick.weatherbot.botapi.handlers.comands.weather.handlers;

import com.umterrick.weatherbot.db.models.telegram.City;
import com.umterrick.weatherbot.db.models.telegram.TelegramUser;
import com.umterrick.weatherbot.db.repositories.UserRepository;
import com.umterrick.weatherbot.enums.BotState;
import com.umterrick.weatherbot.messages.formaters.RealtimeMessageFormatter;
import com.umterrick.weatherbot.weatherApi.models.WeatherData;
import com.umterrick.weatherbot.weatherApi.request.WeatherApiSendRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Slf4j
public class RealtimeHandler {

    private final RealtimeMessageFormatter realtimeMessageFormatter;
    private final UserRepository userRepository;

    private final WeatherApiSendRequest weatherApiSendRequest;

    public RealtimeHandler(RealtimeMessageFormatter realtimeMessageFormatter, UserRepository userRepository, WeatherApiSendRequest weatherApiSendRequest) {
        this.realtimeMessageFormatter = realtimeMessageFormatter;
        this.userRepository = userRepository;
        this.weatherApiSendRequest = weatherApiSendRequest;
    }


    public SendMessage handle(Message message) {

        TelegramUser user = userRepository.findByChatId(message.getChatId());
        City city = user.getMainCity();
        if (city != null) {
            WeatherData cityWeather = weatherApiSendRequest.getWeather(city, 0);
            String messageText = realtimeMessageFormatter.format(cityWeather);
            return new SendMessage(message.getChatId().toString(), messageText);
        }

        return new SendMessage(message.getChatId().toString(), "Спершу задайте місто");

    }

    public BotState getHandlerName() {
        return BotState.TAKE_WEATHER;
    }
}
