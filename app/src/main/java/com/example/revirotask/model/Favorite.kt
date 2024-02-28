package com.example.revirotask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "fav_tbl")
data class Favorite(

    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "lat")
    val latitude: String,

    @ColumnInfo(name = "lon")
    val longitude: String,

    @ColumnInfo(name = "degree")
    val degree: Int,

    @ColumnInfo(name = "dt")
    val dt: Int,

    @ColumnInfo(name = "weatherDescription")
    val weatherDescription: String,

    @ColumnInfo(name = "weatherId")
    val weatherId: Int,

    @ColumnInfo(name = "uvIndex")
    val uvIndex: Double,

    @ColumnInfo(name = "wind")
    val wind: Double,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "hourlyList")
    val hourlyList: List<FavHourly>,
) : Serializable