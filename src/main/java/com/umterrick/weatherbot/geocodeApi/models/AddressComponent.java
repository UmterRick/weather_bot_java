package com.umterrick.weatherbot.geocodeApi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressComponent extends GeocodeModel {
    @JsonProperty("long_name")
    private String cityName;

}
