package com.example.revirotask.ui.fragments.mainPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.revirotask.R
import com.example.revirotask.databinding.ItemWheatherInfoBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.utils.convertUnixTimestampToHourMinute

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
            tvDegree.text = favorite.degree.toString()
            tvCurrentTime.text = convertUnixTimestampToHourMinute(favorite.dt)
            tvDescription.text = favorite.weatherDescription

            ivIcon.setImageResource(
                when (favorite.weatherId) {
                    in 200..699 -> R.drawable.ic_mostly_rainy
                    in 700..799 -> R.drawable.ic_partly_cloudy
                    800 -> R.drawable.ic_mostly_sunny
                    else -> R.drawable.ic_partly_cloudy
                }
            )

            root.setOnClickListener {
                onItemClickListenerForFavorite?.invoke()
            }

            btnDelete.setOnClickListener {
                onItemClickListener?.let { it(favorite) }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Favorite) -> Unit)? = null
    private var onItemClickListenerForFavorite: (() -> Unit)? = null

    fun setOnDeleteClickListener(listener: (Favorite) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnFavoriteClickListener(listener: () -> Unit) {
        onItemClickListenerForFavorite = listener
    }
}