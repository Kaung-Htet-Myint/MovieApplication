package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.GenreAdapter
import com.example.myapplication.databinding.FragmentGenreBinding
import com.example.myapplication.viewmodels.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenreFragment : Fragment() {
    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<GenreFragmentArgs>()

    lateinit var genreAdapter: GenreAdapter

    private val genreViewModel: GenreViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genreTitle = args.name
        binding.tvGenre.text = genreTitle

        genreAdapter = GenreAdapter(onClick = {
            findNavController().navigate(
                GenreFragmentDirections.actionGenreFragmentToDetailFragment(
                    it.id
                )
            )
        })
        binding.rvGenreResult.apply {
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            this.addItemDecoration(GridSpacingItemDecoration(2,spacingInPixels,true))

            this.adapter = genreAdapter
        }

        val genreId = args.id
        genreViewModel.loadDiscover(genreId)
        viewLifecycleOwner.lifecycleScope.launch {
            genreViewModel.getDiscoverFlow?.collectLatest {
                genreAdapter.submitData(it)
            }
        }
    }

}