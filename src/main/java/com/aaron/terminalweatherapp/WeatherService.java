package com.aaron.terminalweatherapp;
// API Stuff
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import com.google.gson.Gson;


public class WeatherService {

    // Will Hold all weather api response values
    WeatherResponse weatherResponse; 
    
    // INstance Variables
    public double latitude; 
    public double longitude;
    public String jsonString;

    // Constructor
    public WeatherService(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setupData(){
        // Gets all the data in place
        String APIString = getAPIString();

        if(setupAPI(APIString).equals("error")) {
            // Do something with this error
        } 

        this.jsonString = setupAPI(APIString);
        System.out.println(this.jsonString);
        gsonService(); // Setups values for easing calling

    }
    
    public String getAPIString(){
        String baseString = "https://api.open-meteo.com/v1/forecast?";
        String coordinatesString = ("latitude="+this.latitude+"&longitude="+this.longitude);
        String additionalString = ("&daily=temperature_2m_max,temperature_2m_min,snowfall_sum,rain_sum,sunrise,sunset&timezone=auto");

        return baseString + coordinatesString + additionalString;
    }

    public String setupAPI(String APIString){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIString))
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());    

            return response.body();
        } catch (Exception e) {
            System.out.println("There's been an error: " + e.getMessage());
            return "error";
        }
    }

    public void gsonService(){
        Gson gson = new Gson();
        weatherResponse = gson.fromJson(this.jsonString, WeatherResponse.class);
    }

    public void outputTodayData(String temperaturePreference) {
        double lowestTemperature = weatherResponse.daily.temperature_2m_min[0];
        double highestTemperature = weatherResponse.daily.temperature_2m_max[0];
        if(temperaturePreference == "c") {
            System.out.println("Temperatures will range from " + (int) Math.round(lowestTemperature) + " to " + (int) Math.round(highestTemperature) + " degrees Celsius.");
        } else {
            lowestTemperature = (lowestTemperature * 9 / 5 + 32);
            highestTemperature = (highestTemperature * 9 / 5 + 32);
            System.out.println("Temperatures will range from " + (int) Math.round(lowestTemperature) + " to " + (int) Math.round(highestTemperature) + " degrees Fahrenheit.");

        }

    }

}
