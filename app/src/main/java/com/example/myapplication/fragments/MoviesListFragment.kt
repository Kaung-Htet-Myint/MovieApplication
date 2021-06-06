package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.MovieListAdapter
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.model.MovieModelImpl
import com.example.myapplication.databinding.FragmentMoviesListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private lateinit var movieModel: MovieModel

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
            findNavController().navigate(MoviesListFragmentDirections.actionFirstFragmentToSecondFragment(it.id))
        })

        binding.rvUpcomingList.apply {
            this.layoutManager = LinearLayoutManager(this@MoviesListFragment.context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = upcomingListAdapter
        }

        val popularListAdapter = MovieListAdapter(onClick = {
           findNavController().navigate(MoviesListFragmentDirections.actionFirstFragmentToSecondFragment(it.id))
        })
        binding.rvPopularList.apply {
            this.layoutManager = LinearLayoutManager(this@MoviesListFragment.context, LinearLayoutManager.HORIZONTAL,false)
            this.adapter = popularListAdapter
        }

        val topRatedListAdapter = MovieListAdapter(onClick = {
            findNavController().navigate(MoviesListFragmentDirections.actionFirstFragmentToSecondFragment(it.id))
        })
        binding.rvTopRated.apply {
            this.layoutManager = LinearLayoutManager(this@MoviesListFragment.context, LinearLayoutManager.HORIZONTAL,false)
            this.adapter = topRatedListAdapter
        }

        movieModel = MovieModelImpl()
        movieModel.getUpcomingMovies(onSuccess = {
            upcomingListAdapter.movieList = it.results
            upcomingListAdapter.notifyDataSetChanged()
        },
        onFailure = {
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })

        movieModel.getPopularMovies(onSuccess = {
            popularListAdapter.movieList = it.results
            popularListAdapter.notifyDataSetChanged()
        },
        onFailure = {
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })

        movieModel.getTopRatedMovies(onSuccess = {
            topRatedListAdapter.movieList = it.results
            topRatedListAdapter.notifyDataSetChanged()
        },
        onFailure = {
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}