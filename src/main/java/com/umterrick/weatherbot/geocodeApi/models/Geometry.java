package com.umterrick.weatherbot.geocodeApi.models;

import lombok.Data;

@Data
public class Geometry extends GeocodeModel {
    private Location location;
}
