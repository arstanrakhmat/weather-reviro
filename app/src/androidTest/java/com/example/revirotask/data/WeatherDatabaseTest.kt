package com.example.revirotask.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.revirotask.model.FavHourly
import com.example.revirotask.model.Favorite
import com.example.revirotask.model.Recent
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDatabaseTest : TestCase() {
    private lateinit var db: WeatherDatabase
    private lateinit var dao: WeatherDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java).build()
        dao = db.weatherDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeAndReadFavorite() = runBlocking {
        val favorite = Favorite(
            "Bishkek", "31.23", "323.3", 32, 1233, "Good",
            12, 12.3, 112.2, 12,
            listOf(
                FavHourly(2134, 21.3, 21)
            )
        )

        dao.insertFavorite(favorite)
        val listOfFavorite = dao.getFavorites().flatMapConcat { it.asFlow() }.toList()

        assertTrue(listOfFavorite.contains(favorite))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeAndReadRecent() = runBlocking {
        val recent = Recent("Bishkek")

        dao.insertToRecent(recent)
        val listOfRecent = dao.getRecentCities().flatMapConcat { it.asFlow() }.toList()

        assertTrue(listOfRecent.contains(recent))
    }
}