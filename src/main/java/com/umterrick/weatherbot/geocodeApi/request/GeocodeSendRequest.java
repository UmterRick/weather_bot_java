package com.umterrick.weatherbot.geocodeApi.request;

import com.umterrick.weatherbot.geocodeApi.models.GeocodeResponse;
import com.umterrick.weatherbot.geocodeApi.models.Geometry;
import com.umterrick.weatherbot.geocodeApi.models.Location;
import com.umterrick.weatherbot.geocodeApi.models.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeocodeSendRequest {

    @Value("${google.geocoding.api.key}")
    private String apiKey;
    @Value("${google.geocoding.api.url}")
    private String url;


    public Location getCoordinates(String city) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format(url, city, apiKey);
        GeocodeResponse geocodeResponse = restTemplate.getForObject(uri, GeocodeResponse.class);

        if (geocodeResponse != null && "OK".equals(geocodeResponse.getStatus()) && !geocodeResponse.getResults().isEmpty()) {
            Result result = geocodeResponse.getResults().get(0);
            Geometry geometry = result.getGeometry();
            if (geometry != null) {
                return geometry.getLocation();
            }
        }
        throw new Exception("Could not get coordinates for city: " + city);
    }
}