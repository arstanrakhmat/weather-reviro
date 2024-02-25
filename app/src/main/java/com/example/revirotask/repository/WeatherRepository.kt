package com.example.revirotask.repository

import com.example.revirotask.data.DataOrException
import com.example.revirotask.model.Weather
import com.example.revirotask.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(
        latitudeQuery: String,
        longitudeQuery: String
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(latitudeQuery, longitudeQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }
}