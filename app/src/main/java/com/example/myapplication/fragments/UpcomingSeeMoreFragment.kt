package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.UpcomingListPagingAdapter
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.model.MovieModelImpl
import com.example.myapplication.databinding.FragmentUpcomingSeemoreBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class UpcomingSeeMoreFragment: Fragment() {
    private var _binding: FragmentUpcomingSeemoreBinding? = null
    private lateinit var movieModel: MovieModel
        private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpcomingSeemoreBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieModel = MovieModelImpl()

        val movieListPaginAdapter = UpcomingListPagingAdapter(onClick = {
            findNavController().navigate(UpcomingSeeMoreFragmentDirections.actionThirdFragmentToSecondFragment(it.id))
        })

        binding.rvSeeMoreDetail.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            this.addItemDecoration(GridSpacingItemDecoration(2,spacingInPixels,true))
            this.adapter = movieListPaginAdapter
        }

       viewLifecycleOwner.lifecycleScope.launch {
           movieModel.getMoviesPagingMovies().collectLatest {
               movieListPaginAdapter.submitData(it)
           }
       }
    }
}