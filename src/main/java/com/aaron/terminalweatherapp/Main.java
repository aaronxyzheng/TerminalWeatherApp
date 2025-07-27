package com.aaron.terminalweatherapp;

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        run();
        input.close();
    }
    public static void run() {

        intro();
        getCity();

        
    }
    public static void intro() {
        System.out.println("Welcome to Weather App!");
    }
    public static void getCity() {

        LocationService locationService = new LocationService();
        boolean cityFound = false;

        while(!cityFound) { // This finds the Location
            // Getting City Name from User
            System.out.println("Enter city name:");
            String cityName = input.nextLine();

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
            
            String userValidation = input.nextLine();

            if(userValidation.equals("y")) {
                return 1;
            } else if (userValidation.equals("n")) {
                return -1;
            } else {
                System.out.println("Looks like you printed something other than y or n");
            }
        }
    }
}


