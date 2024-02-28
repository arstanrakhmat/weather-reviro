# Mobile application for a Weather Dashboard

This project is on creating an Android Weather App using Kotlin. This app allows users to search for
and display the current weather and forecast in their city. To get the weather information I
used <a href="https://openweathermap.org" target="_blank">OpenWeatherMap</a> API.

## Setup

1. **Clone the Repository:** `https://github.com/arstanrakhmat/weather-reviro`
2. **Open in Android Studio:** Open Android Studio and select "Open an existing Android Studio
   project." Navigate to the cloned repository and select the project.
3. **API KEY:** Obtain an API key from <a href="https://openweathermap.org" target="_blank">
   OpenWeatherMap</a>. Replace the placeholder in the `Constants` file with your API key if you
   would like to use your one. By default I add my API_KEY.

## Running the App

Connect your Android device or use an emulator to run the application. Click the "Run" button in
Android Studio to install and launch the app.

## Usage
![Empty Main Screen](app/src/main/res/drawable/empty_main_screen.png?raw=true)

## API Endpoint

Endpoint: `https://api.openweathermap.org/data/3.0/onecall`

## Parameters

- `lat`: Latitude of the location.
- `lon`: Longitude of the location.
- `units`: Optional parameter to specify the units for temperature and other measurements (e.g.,
  metric, imperial).
- `appid`: API key for authentication.

## Data provided

The API response includes a variety of weather information in the app, such as:

- `Current Weather`: Current temperature, weather conditions, humidity, wind speed, etc.
- `Hourly Forecast`: Hourly forecast for the next 48 hours, including temperature, weather
  conditions, and more.

