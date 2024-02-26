package com.example.revirotask.ui.fragments.mainPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.revirotask.databinding.ItemWheatherInfoBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.utils.formatDateTime

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemWheatherInfoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.city == newItem.city
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
        val binding =
            ItemWheatherInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        val favorite = differ.currentList[position]

        holder.binding.apply {
            tvCity.text = favorite.city
            tvDegree.text = favorite.degree
            tvCurrentTime.text = formatDateTime(favorite.dt)
            tvDescription.text = favorite.weatherDescription
            btnDelete.setOnClickListener {
                onItemClickListener?.let { it(favorite) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Favorite) -> Unit)? = null
    fun setOnDeleteClickListener(listener: (Favorite) -> Unit) {
        onItemClickListener = listener
    }
}