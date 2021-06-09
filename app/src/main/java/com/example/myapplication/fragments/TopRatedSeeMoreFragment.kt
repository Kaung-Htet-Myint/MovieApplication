package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapters.TopRatedListPagingAdapter
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.model.MovieModelImpl
import com.example.myapplication.databinding.FragmentTopRatedSeemoreBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopRatedSeeMoreFragment: Fragment() {
        private var _binding: FragmentTopRatedSeemoreBinding? = null
        private val binding get() = _binding!!
        lateinit var movieModel: MovieModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedSeemoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieModel = MovieModelImpl()
        val topRatedSeeMoreAdapter = TopRatedListPagingAdapter(onClick = {
            findNavController().navigate(TopRatedSeeMoreFragmentDirections.actionTopRatedFragmentToSecondFragment(it.id))
        })

        binding.rvTopRatedSeeMore.apply {
            this.layoutManager = GridLayoutManager(context,2)
            this.adapter = topRatedSeeMoreAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieModel.getMoviesPagingMovies().collectLatest {
                topRatedSeeMoreAdapter.submitData(it)
            }
        }
    }
}