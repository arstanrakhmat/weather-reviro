package com.example.revirotask.repository

import com.example.revirotask.data.WeatherDao
import com.example.revirotask.model.Favorite
import com.example.revirotask.model.Recent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val dao: WeatherDao) {

    fun getAllFavorites(): Flow<List<Favorite>> = dao.getFavorites()
    fun getRecentCities(): Flow<List<Recent>> = dao.getRecentCities()
    suspend fun insertFavorite(favorite: Favorite) = dao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = dao.updateFavorite(favorite)
    suspend fun deleteFavorite(favorite: Favorite) = dao.deleteFavorite(favorite)
    suspend fun insertToRecent(recent: Recent) = dao.insertToRecent(recent)
}