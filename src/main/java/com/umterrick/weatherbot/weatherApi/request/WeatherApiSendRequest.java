package com.umterrick.weatherbot.weatherApi.request;

import com.umterrick.weatherbot.db.models.telegram.City;
import com.umterrick.weatherbot.weatherApi.models.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiSendRequest {
    @Value("${weather.api.key}")
    private String apiKey;
    @Value("${weather.api.url}")
    private String url;

    public WeatherInfo getWeather(City city, int days) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        double lat = city.getLatitude();
        double lon = city.getLongitude();
        String uri = String.format(url, apiKey, lat, lon, days);
        WeatherInfo weatherInfo = restTemplate.getForObject(uri, WeatherInfo.class);
        return weatherInfo;
    }
}