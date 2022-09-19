package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapters.FavoritesListAdapter
import com.example.myapplication.databinding.FragmentFavouriteBinding
import com.example.myapplication.viewmodels.FavoriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment(){
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel : FavoriteMoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favMoviesAdapter = FavoritesListAdapter(onClick = {
            findNavController().navigate(FavoriteFragmentDirections.actionFavouriteFragmentToDetailFragment(it.id))
        })

        binding.rvFav.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            this.addItemDecoration(GridSpacingItemDecoration(2,spacingInPixels,true))
            this.adapter = favMoviesAdapter
        }

        favoriteViewModel.loadFavMovies()
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.pagingfavoriteMoviesFlow?.collectLatest {
                favMoviesAdapter.submitData(it)
            }
        }
    }
}