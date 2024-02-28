package com.example.revirotask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recent_tbl")
data class Recent(

    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String
)
