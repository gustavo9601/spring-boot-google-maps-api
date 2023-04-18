package com.gmarquez.direcciones.controllers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {
    @Value("${google.api.key}")
    private String googleApiKey;


    @GetMapping("/google-maps")
    public String example() {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(this.googleApiKey)
                .build();

        GeocodingResult[] results = null;

        try {
            results = GeocodingApi.geocode(context, "Calle 82 # 11 - 37 Bogota").await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (results != null && results.length > 0) {
            double latitud = results[0].geometry.location.lat;
            double longitud = results[0].geometry.location.lng;
            System.out.println("Latitud: " + latitud + ", Longitud: " + longitud);
        }


        return "Hello world";
    }

    @GetMapping("/nominatim")
    public String exampleNominat(){

        return "Hello world";
    }

}
