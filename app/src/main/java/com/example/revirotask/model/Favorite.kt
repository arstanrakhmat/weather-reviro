package com.example.revirotask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class Favorite(

    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "degree")
    val degree: String,

    @ColumnInfo(name = "dt")
    val dt: Int,

    @ColumnInfo(name = "weatherDescription")
    val weatherDescription: String,

    @ColumnInfo(name = "weatherId")
    val weatherId: Int,
)