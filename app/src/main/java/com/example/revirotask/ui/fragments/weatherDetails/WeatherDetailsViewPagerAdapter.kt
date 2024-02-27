package com.example.revirotask.ui.fragments.weatherDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.revirotask.model.Favorite

class WeatherDetailsViewPagerAdapter(
    private val weatherItems: List<Favorite>, fm: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle
) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = weatherItems.size

    override fun createFragment(position: Int): Fragment {
        return WeatherDetailsItemFragment(weatherItems[position])
    }
}