package com.umterrick.weatherbot.weatherApi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherInfo {
    @JsonProperty("current")
    private CurrentWeather currentWeather;

    @JsonProperty("forecast")
    private Forecast forecast;
}
