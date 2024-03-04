package com.example.revirotask.network

import com.example.revirotask.utils.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiTest {
    private lateinit var server: MockWebServer
    private lateinit var api: WeatherApi


    @BeforeEach
    fun beforeEach() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url(Constants.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherApi::class.java)
    }

    @AfterEach
    fun afterEach() {
        server.shutdown()
    }

    @Test
    fun getWeatherAndForecast() = runBlocking {
        val res = MockResponse()
        res.setBody("[]")
        server.enqueue(res)

        val data = api.getWeather("", "")
        server.takeRequest()

        assert(data.body() == null)
    }
}