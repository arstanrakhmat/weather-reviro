package com.example.revirotask.utils

import androidx.room.TypeConverter
import com.example.revirotask.model.FavHourly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyTypeConverters {

    @TypeConverter
    fun fromHourlyList(hourlyList: List<FavHourly>): String {
        val gson = Gson()
        return gson.toJson(hourlyList)
    }

    @TypeConverter
    fun toHourlyList(hourlyList: String): List<FavHourly> {
        val gson = Gson()
        val type = object : TypeToken<List<FavHourly>>() {}.type
        return gson.fromJson(hourlyList, type)
    }
}