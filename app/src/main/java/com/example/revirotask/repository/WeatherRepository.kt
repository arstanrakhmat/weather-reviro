package com.example.revirotask.repository

import com.example.revirotask.model.Weather
import com.example.revirotask.network.WeatherApi
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(
        latitudeQuery: String,
        longitudeQuery: String
    ): Response<Weather> {
        return api.getWeather(latitudeQuery, longitudeQuery)
    }
}