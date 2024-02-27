package com.example.revirotask.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertUnixTimestampToHourMinute(unixTimestamp: Int): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date =
        Date(unixTimestamp.toLong() * 1000) // Multiply by 1000 to convert seconds to milliseconds
    return dateFormat.format(date)
}

fun getHourFromUnixTimestamp(unixTimestamp: Int): String {
    val dateFormat = SimpleDateFormat("HH", Locale.getDefault())
    val date = Date(unixTimestamp.toLong() * 1000)
    return dateFormat.format(date)
}

fun getDateFromUnixTimestamp(unixTimestamp: Int): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Date(unixTimestamp.toLong() * 1000)
    return dateFormat.format(date)
}