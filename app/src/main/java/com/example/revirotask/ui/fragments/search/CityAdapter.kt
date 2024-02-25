package com.example.revirotask.ui.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.revirotask.databinding.ItemCitySearchBinding
import com.example.revirotask.model.City

class CityAdapter(private val cities: List<City>) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCitySearchBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.ViewHolder {
        val binding =
            ItemCitySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
        val city = cities[position]

        holder.binding.apply {
            tvName.text = city.name

            btnAddToFavorites.setOnClickListener {
                onItemClickListener?.let { it(city) }
            }
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    private var onItemClickListener: ((City) -> Unit)? = null
    fun setOnAddToFavoriteClickListener(listener: (City) -> Unit) {
        onItemClickListener = listener
    }

}