package com.aaron.terminalweatherapp;

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    public static LocationService locationService = new LocationService();


    public static void main(String[] args) {
        run();
        input.close();
    }

    public static void run() {

        intro(); // Welcomes User
        getCity(); // Gets User Location and Coordinates
        weatherService(); // Gets weather data for Location

        
    }
    public static void intro() {
        System.out.println("Welcome to Weather App!");
    }
    
    public static void getCity() {

        boolean cityFound = false;

        while(!cityFound) { 
            // Get a City Name from User
            System.out.println("Enter city name:");
            String cityName = input.nextLine();

            System.out.println("Looking for location...");

            // Running Location Service
            int result = locationService.main(cityName);

            if(result == -1) { // Error 
                continue;

            } else if (result == 0) { // No City Returned 
                System.out.println("Looks like that city couldn't be found.");

            } else if (result == 1) { // City Returned

                if(checkCity(locationService) == 1) cityFound = true;        
            }
        }
    }
    
    public static int checkCity(LocationService locationService) {
        while(true) { // Gets from user if this is the correct city
            System.out.println("Results found for " + locationService.displayName);
            System.out.println("Is this correct? (y/n)");
            
            String userValidation = input.nextLine().trim().toLowerCase();

            if(userValidation.equals("y")) {
                System.out.println("Loading Weather Data...");
                return 1;
            } else if (userValidation.equals("n")) {
                return -1;
            } else {
                System.out.println("Looks like you printed something other than y or n");
            }
        }
    }
    
    public static void weatherService() {
        WeatherService weatherService = new WeatherService(locationService.latitude, locationService.longitude);
        weatherService.setupData();

        while(true) {
            System.out.println("Would you like to use Farhrenheit or Celsius? (f/c)");
            String temperaturePreference = input.nextLine().trim().toLowerCase();
            if(temperaturePreference.equals("f") || temperaturePreference.equals("c")) {
                weatherService.outputTodayData(temperaturePreference);
                break;
            } else {
                System.out.println("You've entered something other than 'f' or 'c'");
            }

        }
        

    }
}


