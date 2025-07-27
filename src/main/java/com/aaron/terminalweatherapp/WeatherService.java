package com.aaron.terminalweatherapp;
// API Stuff
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;


public class WeatherService {
    public double latitude;
    public double longitude;

    public WeatherService(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void main(){
        if(setupAPI(getAPIString()).equals("error")) {
            // Do something with this error
        } 

        String jsonString = setupAPI(getAPIString());
        System.out.println(jsonString);

    }
    public String getAPIString(){
        String baseString = "https://api.open-meteo.com/v1/forecast?";
        String coordinatesString = ("latitude="+latitude+"&longitude="+longitude);
        String additionalString = ("&hourly=temperature_2m,precipitation_probability,rain,relative_humidity_2m");

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




}
