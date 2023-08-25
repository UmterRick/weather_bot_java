package com.umterrick.weatherbot.db.models.geocode;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Location {
    private String lat;
    private String lng;
}
