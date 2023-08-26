package com.umterrick.weatherbot.weatherApi.geocode.models;

import lombok.Data;

import java.util.List;

@Data
public class GeocodeResponse extends GeocodeModel {
    private List<Result> results;
    private String status;
}
