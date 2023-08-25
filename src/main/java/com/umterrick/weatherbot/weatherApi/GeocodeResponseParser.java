package com.umterrick.weatherbot.weatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umterrick.weatherbot.db.models.geocode.GeocodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponseParser {

    @Value("${google.geocoding.api.key}")
    private String key;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public GeocodeResponse parseName(String json) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        GeocodeResponse response = mapper.readValue(json, GeocodeResponse.class);
        return response;

    }

}
