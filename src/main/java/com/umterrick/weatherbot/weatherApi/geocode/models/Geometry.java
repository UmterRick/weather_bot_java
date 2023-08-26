package com.umterrick.weatherbot.weatherApi.geocode.models;

import lombok.Data;

@Data
public class Geometry extends GeocodeModel {
    private Location location;
}
