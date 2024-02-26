package com.example.revirotask.network

import com.example.revirotask.model.Weather
import com.example.revirotask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "data/3.0/onecall")
    suspend fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String = "minutely,alerts,daily",
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.API_KEY,
    ): Response<Weather>
}