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
import com.example.myapplication.adapters.TopRatedListPagingAdapter
import com.example.myapplication.databinding.FragmentTopRatedSeemoreBinding
import com.example.myapplication.viewmodels.TopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class TopRatedSeeMoreFragment : Fragment() {
    private var _binding: FragmentTopRatedSeemoreBinding? = null
    private val binding get() = _binding!!
    private val topRatedViewModel: TopRatedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedSeemoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topRatedSeeMoreAdapter = TopRatedListPagingAdapter(onClick = {
            findNavController().navigate(
                TopRatedSeeMoreFragmentDirections.actionTopRatedFragmentToSecondFragment(
                    it.id
                )
            )
        })

        topRatedViewModel.loadTopRated("topRated")
        viewLifecycleOwner.lifecycleScope.launch {
            topRatedViewModel.topRatedMovieFlow?.collectLatest {
                topRatedSeeMoreAdapter.submitData(it)
            }
        }

        binding.rvTopRatedSeeMore.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            this.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, true))

            this.adapter = topRatedSeeMoreAdapter
        }

        /*viewLifecycleOwner.lifecycleScope.launch {
            movieModel.getMoviesPagingMovies().collectLatest {
                topRatedSeeMoreAdapter.submitData(it)
            }
        }*/
    }
}