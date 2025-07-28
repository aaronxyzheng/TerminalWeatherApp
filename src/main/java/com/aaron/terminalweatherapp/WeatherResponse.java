package com.aaron.terminalweatherapp;

public class WeatherResponse {
    double latitude;
    double longitude;
    double generationtime_ms;
    int utc_offset_seconds;
    String timezone;
    String timezone_abbreviation;
    double elevation;
    DailyUnits daily_units;
    Daily daily;
    

}
class DailyUnits {
    String time;
    String temperature_2m_max;
    String temperature_2m_min;
    String snowfall_sum;
    String rain_sum;
    String sunrise;
    String sunset;
}
class Daily {
    String[] time;
    Double[] temperature_2m_max;
    Double[] temperature_2m_min;
    Double[] snowfall_sum;
    Double[] rain_sum;
    String[] sunrise;
}