package com.aaron.terminalweatherapp;

// Imports for the API
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

//Imports for GSON
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName; // Cause API returns city name with key display_name

// This class tasks City Name and turns it into coordinates so Web API can Use
public class LocationService {
    public double longitude;
    public double latitude;
    @SerializedName("display_name")
    public String displayName;

    // Takes a string of city name and uses API to set the longitude and latitude instance variables of the object
    public int main(String cityName) {

        cityName = cityName.replace(" ","%20"); // Replaces the spaces 
        String responseString;

        try {
            // Makes sure there's nothing wrong with API
            if(setupAPI(cityName).equals("error in API")) return -1;
            

            responseString = setupAPI(cityName);
            
            if(responseString == "[]") {
                return 0; // Returns no value
            } else {
                gsonService(responseString); // Sets the Instance Variables
                return 1; // Return Successful
            }

        } catch (Exception e) {
            System.out.println("There's been an error: " + e.getMessage());
            return -1;
        }
    }
    public String setupAPI(String cityName) {
    // This method Manages the API stuff and returns the Json String
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://nominatim.openstreetmap.org/search?q=" + cityName + "&format=json&limit=1"))
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        
        } catch (Exception e) {
            System.out.println("There's been an error: " + e.getMessage());
            return "error in API";
        }
        

        
    }
    public void gsonService(String jsonString) {
        Gson gson = new Gson();
        LocationService[] valueHolder = gson.fromJson(jsonString, LocationService[].class);

        this.latitude = valueHolder[0].latitude;
        this.longitude = valueHolder[0].longitude;
        this.displayName = valueHolder[0].displayName;
    }
}


