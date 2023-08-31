package com.umterrick.weatherbot.geocodeApi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Location extends GeocodeModel {

    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;

}
