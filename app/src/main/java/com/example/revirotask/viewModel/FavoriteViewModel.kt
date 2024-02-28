package com.example.revirotask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.revirotask.model.Favorite
import com.example.revirotask.model.Recent
import com.example.revirotask.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) :
    ViewModel() {

    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    private val _recentList = MutableStateFlow<List<Recent>>(emptyList())
    val recentList = _recentList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDbRepository.getAllFavorites().distinctUntilChanged().collect { listOfFavs ->
                _favList.value = listOfFavs
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDbRepository.getRecentCities().distinctUntilChanged().collect { listOfRecent ->
                _recentList.value = listOfRecent
            }
        }
    }

//    fun getListOfRecent(): List<Recent> {
//        var listRecent = emptyList<Recent>()
//        viewModelScope.launch(Dispatchers.IO) {
//            weatherDbRepository.getRecentCities().distinctUntilChanged().collect { listOfRecent ->
//                _recentList.value = listOfRecent
//                listRecent = listOfRecent
//            }
//        }
//
//        return listRecent
//    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        weatherDbRepository.insertFavorite(favorite)
    }

    fun insertToRecent(recent: Recent) =
        viewModelScope.launch { weatherDbRepository.insertToRecent(recent) }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        weatherDbRepository.updateFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        weatherDbRepository.deleteFavorite(favorite)
    }
}