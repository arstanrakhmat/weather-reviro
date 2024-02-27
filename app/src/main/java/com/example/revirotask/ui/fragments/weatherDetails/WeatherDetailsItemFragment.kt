package com.example.revirotask.ui.fragments.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.revirotask.R
import com.example.revirotask.databinding.FragmentWeatherDetailsItemBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.ui.fragments.BaseFragment
import com.example.revirotask.utils.getDateFromUnixTimestamp
import com.example.revirotask.utils.getHourFromUnixTimestamp

class WeatherDetailsItemFragment(private val weatherDetail: Favorite) :
    BaseFragment<FragmentWeatherDetailsItemBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWeatherDetailsItemBinding {
        return FragmentWeatherDetailsItemBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCityName.text = weatherDetail.city
        binding.tvDegree.text = weatherDetail.degree.toString()
        binding.tvDate.text = getDateFromUnixTimestamp(weatherDetail.dt)
        binding.tvUvIndex.text = weatherDetail.uvIndex.toString()
        binding.tvWind.text = weatherDetail.wind.toString() + " m/s"
        binding.tvHumidity.text = weatherDetail.humidity.toString() + "%"

        binding.tvNowDegree.text = weatherDetail.hourlyList[0].temp.toInt().toString()+ "°"
        binding.weatherIconNow.setImageResource(
            getDrawableByCode(
                weatherDetail.hourlyList[0].weatherId
            )
        )

        binding.tvDegreeWhatTime1.text = getHourFromUnixTimestamp(weatherDetail.hourlyList[1].dt)
        binding.tvDegreeTime1.text = weatherDetail.hourlyList[1].temp.toInt().toString()+ "°"
        binding.weatherIcon1.setImageResource(
            getDrawableByCode(
                weatherDetail.hourlyList[1].weatherId
            )
        )

        binding.tvDegreeWhatTime2.text = getHourFromUnixTimestamp(weatherDetail.hourlyList[2].dt)
        binding.tvDegreeTime2.text = weatherDetail.hourlyList[2].temp.toInt().toString()+ "°"
        binding.weatherIcon2.setImageResource(
            getDrawableByCode(
                weatherDetail.hourlyList[2].weatherId
            )
        )

        binding.tvDegreeWhatTime3.text = getHourFromUnixTimestamp(weatherDetail.hourlyList[3].dt)
        binding.tvDegreeTime3.text = weatherDetail.hourlyList[3].temp.toInt().toString() + "°"
        binding.weatherIcon3.setImageResource(
            getDrawableByCode(
                weatherDetail.hourlyList[3].weatherId
            )
        )

        binding.tvDegreeWhatTime4.text = getHourFromUnixTimestamp(weatherDetail.hourlyList[4].dt)
        binding.tvDegreeTime4.text = weatherDetail.hourlyList[4].temp.toInt().toString()+ "°"
        binding.weatherIcon4.setImageResource(
            getDrawableByCode(
                weatherDetail.hourlyList[4].weatherId
            )
        )
    }

    private fun getDrawableByCode(weatherCode: Int): Int {
        val drawableResId = when (weatherCode) {
            in 200..699 -> R.drawable.ic_small_rainy
            in 700..799 -> R.drawable.ic_small_cloudy
            800 -> R.drawable.ic_uv_index
            else -> R.drawable.ic_small_cloudy
        }

        return drawableResId
    }

}