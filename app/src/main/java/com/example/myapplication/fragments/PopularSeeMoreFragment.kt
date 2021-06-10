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
import com.example.myapplication.adapters.PopularListPagingAdapter
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.model.MovieModelImpl
import com.example.myapplication.databinding.FragmentPopularSeemoreBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularSeeMoreFragment: Fragment() {
    private var _binding: FragmentPopularSeemoreBinding? = null
    private val binding get() = _binding!!
    lateinit var movieModel: MovieModel

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

        movieModel = MovieModelImpl()
        val popularListPagingAdapter = PopularListPagingAdapter(onClick ={
            findNavController().navigate(PopularSeeMoreFragmentDirections.actionPopularFragementToSecondFragment(it.id))
        })

        binding.rvPopularSeemore.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            this.addItemDecoration(GridSpacingItemDecoration(2,spacingInPixels,true))

            this.adapter = popularListPagingAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieModel.getMoviesPagingMovies().collectLatest {
                popularListPagingAdapter.submitData(it)
            }
        }
    }
}