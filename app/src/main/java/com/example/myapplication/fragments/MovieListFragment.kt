package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.MovieBannerAdpter
import com.example.myapplication.adapters.MovieListAdapter
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.model.MovieModelImpl
import com.example.myapplication.data.vos.TrendingResultVO
import com.example.myapplication.databinding.FragmentMoviesListBinding
import com.example.myapplication.domain.Trending
import com.example.myapplication.viewmodels.MovieListViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null

    private val upComingViewModel : MovieListViewModel by viewModels()
    private val popularViewModel : MovieListViewModel by viewModels()
    private val topRatedViewModel : MovieListViewModel by viewModels()
    private val allTrendingViewModel : MovieListViewModel by viewModels()

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

        allTrendingViewModel.loadAllTrending(mediaType = "all", timeWindow = "day")
        allTrendingViewModel.allTrendingLiveData.observe(viewLifecycleOwner){
            setupViewPager(it)
        }

        upComingViewModel.loadUpComingList()
        upComingViewModel.upComingMoviesLiveData.observe(viewLifecycleOwner){
            upcomingListAdapter.submitList(it.results)
        }

        popularViewModel.loadPopularList()
        popularViewModel.popularMoviesLiveData.observe(viewLifecycleOwner){
            popularListAdapter.submitList(it.results)
        }

        topRatedViewModel.loadTopRatedList()
        topRatedViewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner){
            topRatedListAdapter.submitList(it.results)
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