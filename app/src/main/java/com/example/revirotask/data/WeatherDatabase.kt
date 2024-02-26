package com.example.revirotask.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.revirotask.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}