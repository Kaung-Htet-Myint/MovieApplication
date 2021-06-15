package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.TrendingListAdapter
import com.example.myapplication.databinding.FragmentTrendingBinding
import com.example.myapplication.utils.ViewState
import com.example.myapplication.viewmodels.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment: Fragment() {
    private var _binding: FragmentTrendingBinding? = null
    private val binding get() = _binding!!

    lateinit var trendingMovieListAdapter: TrendingListAdapter
    lateinit var trendingTvListAdapter: TrendingListAdapter
    lateinit var trendingPersonAdapter: TrendingListAdapter

    private val trendingViewModel : TrendingViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendingMovieListAdapter = TrendingListAdapter(onClick = {
            Toast.makeText(context,"Trending",Toast.LENGTH_LONG).show()
        })
        binding.rvTrendingMoviesList.apply {
            this.layoutManager = LinearLayoutManager(this@TrendingFragment.context,LinearLayoutManager.HORIZONTAL,false)
            this.adapter = trendingMovieListAdapter
        }

        trendingTvListAdapter = TrendingListAdapter(onClick = {
            Toast.makeText(context,"Trending TV", Toast.LENGTH_LONG).show()
        })
        binding.rvTvList.apply {
            this.layoutManager = LinearLayoutManager(this@TrendingFragment.context,LinearLayoutManager.HORIZONTAL,false)
            this.adapter = trendingTvListAdapter
        }

        trendingPersonAdapter = TrendingListAdapter(onClick = {
            Toast.makeText(context,"Trending People", Toast.LENGTH_LONG).show()
        })
        binding.rvPeopleList.apply {
            this.layoutManager = LinearLayoutManager(this@TrendingFragment.context,LinearLayoutManager.HORIZONTAL,false)
            this.adapter = trendingPersonAdapter
        }

        trendingViewModel.trendingMovieLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbTrending.isVisible = true
                    binding.gpTrending.isVisible = false
                }
                is ViewState.Successs ->{
                    binding.pbTrending.isVisible = false
                    binding.gpTrending.isVisible = true

                    trendingMovieListAdapter.submitList(it.data)
                }
                is ViewState.Error ->{
                    binding.pbTrending.isVisible = false
                    Toast.makeText(requireContext(),it.error.message,Toast.LENGTH_LONG).show()
                }
            }

            //binding.pbTrendingMovies.isVisible = true
        }

        trendingViewModel.trendingTvLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbTrending.isVisible = true
                    binding.gpTrending.isVisible = false
                }
                is ViewState.Successs ->{
                    binding.pbTrending.isVisible = false
                    binding.gpTrending.isVisible = true

                    trendingTvListAdapter.submitList(it.data)
                }
                is ViewState.Error ->{
                    binding.pbTrending.isVisible = false
                    Toast.makeText(requireContext(),it.error.message,Toast.LENGTH_LONG).show()
                }
            }
            //binding.pbTrendingMovies.isVisible = true
        }

        trendingViewModel.trendingPersonLiveData.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Loading -> {
                    binding.pbTrending.isVisible = true
                    binding.gpTrending.isVisible = false
                }
                is ViewState.Successs ->{
                    binding.pbTrending.isVisible = false
                    binding.gpTrending.isVisible = true

                    trendingPersonAdapter.submitList(it.data)
                }
                is ViewState.Error ->{
                    binding.pbTrending.isVisible = false
                    Toast.makeText(requireContext(),it.error.message,Toast.LENGTH_LONG).show()
                }
            }
            //binding.pbTrendingMovies.isVisible = true
        }

        trendingViewModel.loadTrending("movie", "day")
        trendingViewModel.loadTrending("tv", "day")
        trendingViewModel.loadTrending("person", "day")


        binding.chipGroupMovieFilter.setOnCheckedChangeListener { group, checkedId ->
            when(getCheckedChip(checkedId)){
                "day" -> trendingViewModel.loadTrending("movie", "day")
                "week"-> trendingViewModel.loadTrending("movie", "week")
                else -> trendingViewModel.loadTrending("movie", "day")
            }
        }

        binding.chipGroupTvFilter.setOnCheckedChangeListener { group, checkedId ->
            when(getCheckedChip(checkedId)){
                "day" -> trendingViewModel.loadTrending("tv", "day")
                "week" -> trendingViewModel.loadTrending("tv", "week")
                else -> trendingViewModel.loadTrending("tv", "day")
            }
        }

        binding.chipGroupPersonFilter.setOnCheckedChangeListener { group, checkedId ->
            when(getCheckedChip(checkedId)){
                "day" -> trendingViewModel.loadTrending("person", "day")
                "week" -> trendingViewModel.loadTrending("person", "week")
                else -> trendingViewModel.loadTrending("person", "day")
            }
        }
    }

    fun getCheckedChip(id: Int): String{
        when(id){
            R.id.chipMovieDay -> return "day"
            R.id.chipMovieWeek -> return "week"
            R.id.chipTvDay -> return "day"
            R.id.chipTvWeek -> return "week"
            R.id.chipPersonDay -> return "day"
            R.id.chipPersonWeek -> return "week"
            else -> return "all"
        }
    }
}