package com.example.revirotask.model


data class FavHourly(
    val dt: Int,
    val temp: Double,
    val weatherId: Int
)

fun mapHourlyToFavHourly(hourlyList: List<Hourly>): List<FavHourly> {
    return hourlyList.map {
        FavHourly(
            dt = it.dt,
            temp = it.temp,
            weatherId = it.weather.getOrNull(0)?.id ?: 0
        )
    }
}
