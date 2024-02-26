package com.example.revirotask.ui.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.revirotask.databinding.ItemCitySearchBinding
import com.example.revirotask.model.City

class CityAdapter(private val cities: ArrayList<City>) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    val initialCityDataList = ArrayList<City>().apply {
        addAll(cities)
    }

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

    fun getFilter() : Filter {
        return cityFilter
    }

    private val cityFilter = object : android.widget.Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<City> = ArrayList()
            if (constraint.isNullOrEmpty()) {
                initialCityDataList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase()
                initialCityDataList.forEach {
                    if (it.name.lowercase().contains(query)) {
                        filteredList.add(it)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                cities.clear()
                cities.addAll(results.values as ArrayList<City>)
                notifyDataSetChanged()
            }
        }
    }

    private var onItemClickListener: ((City) -> Unit)? = null
    fun setOnAddToFavoriteClickListener(listener: (City) -> Unit) {
        onItemClickListener = listener
    }

}