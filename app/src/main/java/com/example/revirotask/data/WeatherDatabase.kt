package com.example.revirotask.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.revirotask.model.Favorite
import com.example.revirotask.utils.MyTypeConverters

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
@TypeConverters(MyTypeConverters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}