package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.MovieListAdapter
import com.example.myapplication.adapters.SearchResultListAdapter
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val searchViewModel: SearchViewModel by viewModels()

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchResult = SearchResultListAdapter(onClick = {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.id))
        })

        binding.rvSearchResult.apply {
            this.layoutManager = LinearLayoutManager(this@SearchFragment.context, LinearLayoutManager.VERTICAL,false)
            this.adapter = searchResult
        }

        binding.etMovieName.setOnClickListener {
            val query = binding.etMovieName.text.toString()
            searchViewModel.loadSearchMovies(query)
        }

        searchViewModel.allSearchMovies.observe(viewLifecycleOwner){
            searchResult.submitList(it)
        }
    }
}