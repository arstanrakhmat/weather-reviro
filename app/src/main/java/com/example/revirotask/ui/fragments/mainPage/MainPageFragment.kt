package com.example.revirotask.ui.fragments.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revirotask.R
import com.example.revirotask.databinding.FragmentMainPageBinding
import com.example.revirotask.ui.fragments.BaseFragment
import com.example.revirotask.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    private lateinit var favoritesAdapter: FavoritesAdapter
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        clickListener()
    }

    private fun clickListener() {
        binding.apply {
            btnAddCity.setOnClickListener {
                findNavController().navigate(R.id.searchFragment)
            }

            btnMenu.setOnClickListener {
                findNavController().navigate(R.id.weatherDetailsFragment)
            }

            favoritesAdapter.setOnDeleteClickListener { favorite ->
                favoriteViewModel.deleteFavorite(favorite)
                // Manually update RecyclerView after deletion
                val updatedList = favoriteViewModel.favList.value
                favoritesAdapter.differ.submitList(updatedList)

                Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setupRv() {
        favoritesAdapter = FavoritesAdapter()
        binding.rvChosenWeathers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoritesAdapter
        }

        favoriteViewModel.loadingState.onEach { isLoading ->
            if (!isLoading) {
                val listOfWeatherValues = favoriteViewModel.favList.value
                favoritesAdapter.differ.submitList(listOfWeatherValues)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}