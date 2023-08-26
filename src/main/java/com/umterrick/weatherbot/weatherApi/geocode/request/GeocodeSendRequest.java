package com.umterrick.weatherbot.weatherApi.geocode.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeocodeSendRequest {
    @Value("${google.geocoding.api.key}")
    private String apiKey;


}
