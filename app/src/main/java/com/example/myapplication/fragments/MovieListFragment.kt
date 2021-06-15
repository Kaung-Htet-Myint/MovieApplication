package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.MovieBannerAdpter
import com.example.myapplication.adapters.MovieListAdapter
import com.example.myapplication.databinding.FragmentMoviesListBinding
import com.example.myapplication.domain.Trending
import com.example.myapplication.utils.ViewState
import com.example.myapplication.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val movieViewModel : MovieListViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val upcomingListAdapter = MovieListAdapter(onClick = {
            //Toast.makeText(requireContext(),"Testing",Toast.LENGTH_LONG).show()
            findNavController().navigate(MovieListFragmentDirections.actionFirstFragmentToSecondFragment(it.id))
        })

        binding.rvUpcomingList.apply {
            this.layoutManager = LinearLayoutManager(this@MovieListFragment.context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = upcomingListAdapter
        }

        val popularListAdapter = MovieListAdapter(onClick = {
           findNavController().navigate(MovieListFragmentDirections.actionFirstFragmentToSecondFragment(it.id))
        })
        binding.rvPopularList.apply {
            this.layoutManager = LinearLayoutManager(this@MovieListFragment.context, LinearLayoutManager.HORIZONTAL,false)
            this.adapter = popularListAdapter
        }

        val topRatedListAdapter = MovieListAdapter(onClick = {
            findNavController().navigate(MovieListFragmentDirections.actionFirstFragmentToSecondFragment(it.id))
        })
        binding.rvTopRated.apply {
            this.layoutManager = LinearLayoutManager(this@MovieListFragment.context, LinearLayoutManager.HORIZONTAL,false)
            this.adapter = topRatedListAdapter
        }

        movieViewModel.loadAllTrending(mediaType = "all", timeWindow = "day")
        movieViewModel.allTrendingLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbMovieList.isVisible = true
                    binding.gpMovieList.isVisible = false
                }
                is ViewState.Successs -> {
                    binding.pbMovieList.isVisible = false
                    binding.gpMovieList.isVisible = true

                    setupViewPager(it.data)
                }
                is ViewState.Error -> {
                    binding.pbMovieList.isVisible = false
                    Toast.makeText(requireContext(),it.error.message,Toast.LENGTH_LONG).show()
                }
            }

        }

        movieViewModel.loadUpComingList()
        movieViewModel.upComingMoviesLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading ->{
                    binding.pbMovieList.isVisible = true
                    binding.gpMovieList.isVisible = false
                }
                is ViewState.Successs ->{
                    binding.pbMovieList.isVisible = false
                    binding.gpMovieList.isVisible = true
                    upcomingListAdapter.submitList(it.data.results)
                }
                is ViewState.Error ->{
                    binding.pbMovieList.isVisible = false
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        movieViewModel.loadPopularList()
        movieViewModel.popularMoviesLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading ->{
                    binding.pbMovieList.isVisible = true
                    binding.gpMovieList.isVisible = false
                }
                is ViewState.Successs ->{
                    binding.pbMovieList.isVisible = false
                    binding.gpMovieList.isVisible = true
                    popularListAdapter.submitList(it.data.results)
                }
                is ViewState.Error ->{
                    binding.pbMovieList.isVisible = false
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }

        }

        movieViewModel.loadTopRatedList()
        movieViewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading ->{
                    binding.pbMovieList.isVisible = true
                    binding.gpMovieList.isVisible = false
                }
                is ViewState.Successs ->{
                    binding.pbMovieList.isVisible = false
                    binding.gpMovieList.isVisible = true
                    topRatedListAdapter.submitList(it.data.results)
                }
                is ViewState.Error ->{
                    binding.pbMovieList.isVisible = false
                   Toast.makeText(requireContext(),it.error.message, Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.ivSeeMore.setOnClickListener {
            findNavController().navigate(MovieListFragmentDirections.actionFirstFragmentToThirdFragment())
        }

        binding.ivPopularSeeMore.setOnClickListener {
            findNavController().navigate(MovieListFragmentDirections.actionFirstFragmentToPopularSeeMoreFragment())
        }

        binding.ivTopRatedSeeMore.setOnClickListener {
            findNavController().navigate(MovieListFragmentDirections.actionFirstFragmentToTopRatedSeeMoreFragment())
        }
    }

    private fun setupViewPager(result: List<Trending>){
        binding.bannerView.let {
            it.setAdapter(MovieBannerAdpter())
            it.setLifecycleRegistry(lifecycle)
        }.create(result)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}