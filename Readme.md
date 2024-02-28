# Mobile application for a Weather Dashboard

This project is on creating an Android Weather App using Kotlin. This app allows users to search for
all capital cities of the world, save favorite cities
and display the current weather and forecast of chosen cities. To get the weather information I
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
It is the main screen of the app that display your saved cities. In the current state there are not
favorites and chosen cities, that is why the screen is empty. To search capital cities and chose
your favorite ones you should click `Plus` button

![Default Search Screen](app/src/main/res/drawable/default_search_screen.png?raw=true)
![SearchView Screen](app/src/main/res/drawable/search_view_active.png?raw=true)
It is `Search Screen`. Here you can use search view to find any capital city you want, save it to
the database, and see the latest searched cities in `ChipGroup`

![Main Screen With Data](app/src/main/res/drawable/main_screen_with_data.png?raw=true)
It is again the main screen, but in this time with saved cities to database. You are allowed to
refresh data of cities from network to database by swiping down the screen. Moreover, you can remove
city
from database using `Delete` or By clicking to City you can move the next screen with detail
information

![Main Screen With Data](app/src/main/res/drawable/detail_info.png?raw=true)
In this screen you can see more detailed information of `each saved city`. In this screen there such
info as: `humidity`, `wind speed`, `uv index` and `the neares hourly forecast`

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

