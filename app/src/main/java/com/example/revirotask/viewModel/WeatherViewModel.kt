package com.example.revirotask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.revirotask.data.Resource
import com.example.revirotask.model.Weather
import com.example.revirotask.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val _weatherData: MutableLiveData<Resource<Weather>> = MutableLiveData()
    val weatherData: MutableLiveData<Resource<Weather>>
        get() = _weatherData

    fun getWeatherData(
        latitudeQuery: String,
        longitudeQuery: String
    ) {
        viewModelScope.launch {
            _weatherData.postValue(Resource.Loading())
            val response = repository.getWeather(latitudeQuery, longitudeQuery)
            _weatherData.postValue(handleWeatherData(response))
        }
    }

    private fun handleWeatherData(response: Response<Weather>): Resource<Weather> {
        if (response.isSuccessful) {
            response.body()?.let { res ->
                return Resource.Success(res)
            }
        }
        return Resource.Error(response.message())
    }

}