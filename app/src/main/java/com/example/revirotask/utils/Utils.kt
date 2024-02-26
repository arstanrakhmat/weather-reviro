package com.example.revirotask.utils

import java.text.SimpleDateFormat
import java.util.Date

fun formatDateTime(timeStamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timeStamp.toLong() * 1000)

    return sdf.format(date)
}