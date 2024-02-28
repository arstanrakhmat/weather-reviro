# Mobile application for a Weather Dashboard

This project is on creating an Android Weather App using Kotlin. This app allows users to search for
and display the current weather and forecast in their city. To get the weather information I
used <a href="https://openweathermap.org" target="_blank">OpenWeatherMap</a> API.


## Setup
1. **Clone the Repository:** ```https://github.com/arstanrakhmat/weather-reviro```

## API Endpoint
Endpoint: `https://api.openweathermap.org/data/3.0/onecall`

## Parameters
- `lat`: Latitude of the location.
- `lon`: Longitude of the location.
- `units`: Optional parameter to specify the units for temperature and other measurements (e.g., metric, imperial).
- `appid`: API key for authentication.

## Parameters
The API response includes a variety of weather information in the app, such as:

- `Current Weather`: Current temperature, weather conditions, humidity, wind speed, etc.
- `Hourly Forecast`: Hourly forecast for the next 48 hours, including temperature, weather conditions, and more.

