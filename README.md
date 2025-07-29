# Terminal Weather App

A simple command-line weather application that fetches current weather data for any city worldwide.

## Features

- 🌍 **City Lookup**: Search for weather by city name using OpenStreetMap geocoding
- 🌡️ **Temperature Display**: Shows daily temperature ranges in Celsius or Fahrenheit
- ✅ **Location Verification**: Confirms the correct city before fetching weather data
- 🔄 **Error Handling**: Graceful handling of API errors and invalid inputs
- 📱 **User-Friendly Interface**: Simple terminal-based interaction

## How It Works

1. **Enter a city name** - The app searches for your location
2. **Confirm the location** - Verify it found the right place
3. **Choose temperature unit** - Fahrenheit or Celsius
4. **Get your weather** - See today's temperature range

## APIs Used

- **OpenStreetMap Nominatim API** - For geocoding city names to coordinates
- **Open-Meteo Weather API** - For fetching weather data

## Sample Output
Welcome to Weather App!
Enter city name:

Chicago
Looking for location...
Results found for Chicago, Illinois, United States
Is this correct? (y/n)
y
Loading Weather Data...
Would you like to use Fahrenheit or Celsius? (f/c)
f
Temperatures will range from 45 to 67 degrees Fahrenheit.


## Project Structure
src/
├── Main.java              # Application entry point and user interface
├── LocationService.java   # Handles city lookup and geocoding
├── WeatherService.java    # Fetches and processes weather data
└── WeatherResponse.java   # Data models for weather API response

## Dependencies

- **Java 11+** - Uses HTTP Client for API requests
- **Gson** - For JSON parsing

## Technical Details

- **Language**: Java
- **Architecture**: Object-oriented with separation of concerns
- **Error Handling**: Try-catch blocks with user-friendly error messages
- **Data Flow**: City name → Coordinates → Weather data → Formatted output

## What I Learned

- Working with REST APIs in Java
- JSON parsing with Gson
- HTTP client usage
- Object-oriented design principles
- User input validation and error handling
