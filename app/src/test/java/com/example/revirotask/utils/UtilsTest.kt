package com.example.revirotask.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun testConvertUnixTimestampToHourMinute() {
        val unixTimestamp = 1614889200
        val result = convertUnixTimestampToHourMinute(unixTimestamp)
        assertEquals("Expected result", result)
    }

    @Test
    fun testGetHourFromUnixTimestamp() {
        val unixTimestamp = 1614889200
        val result = getHourFromUnixTimestamp(unixTimestamp)
        assertEquals("Expected result", result)
    }

    @Test
    fun testGetDateFromUnixTimestamp() {
        val unixTimestamp = 1614889200
        val result = getDateFromUnixTimestamp(unixTimestamp)
        assertEquals("Expected result", result)
    }
}