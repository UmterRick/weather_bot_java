package com.umterrick.weatherbot.weatherApi.geocode.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Location extends GeocodeModel {
    private String lat;
    private String lng;
}
