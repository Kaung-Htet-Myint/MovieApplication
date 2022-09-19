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
import com.example.myapplication.adapters.PopularListPagingAdapter
import com.example.myapplication.databinding.FragmentPopularSeemoreBinding
import com.example.myapplication.viewmodels.PagingPopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class PopularSeeMoreFragment: Fragment() {
    private var _binding: FragmentPopularSeemoreBinding? = null
    private val binding get() = _binding!!
    private val popularViewModel: PagingPopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularSeemoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularListPagingAdapter = PopularListPagingAdapter(onClick ={
            findNavController().navigate(PopularSeeMoreFragmentDirections.actionPopularSeeMoreFragementToDetailFragment(it.id))
        })

        popularViewModel.loadPopular("popular")

        viewLifecycleOwner.lifecycleScope.launch {
            popularViewModel.pagingUpComingMoviesFlow?.collectLatest {
                popularListPagingAdapter.submitData(it)
            }
        }

        binding.rvPopularSeemore.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            this.addItemDecoration(GridSpacingItemDecoration(2,spacingInPixels,true))

            this.adapter = popularListPagingAdapter
        }
    }
}