package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentMovieDetailBinding
import com.example.myapplication.utils.ViewState
import com.example.myapplication.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val viewModel: MovieDetailViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        //Toast.makeText(requireContext(),args.id.toString(),Toast.LENGTH_LONG).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/

        viewModel.loadDetail(args.id)
        viewModel.detailLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> {
                    binding.pbLoading.isVisible = true
                    binding.gpMovieDetail.isVisible = false
                }

                is ViewState.Successs -> {
                    binding.pbLoading.isVisible = false
                    binding.gpMovieDetail.isVisible = true

                    val url = "https://image.tmdb.org/t/p/original/" + it.data.backdrop_path
                    Glide.with(requireContext()).load(url)
                        .into(binding.ivMovieDetail)
                    binding.tvDetailTitle.setText(it.data.original_title)
                    binding.tvLenguage.setText(it.data.original_language)
                    binding.tvOverview.setText(it.data.overview)
                    binding.tvRating.setText(it.data.popularity.toString())
                }

                is ViewState.Error -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(requireContext(),it.error.message, Toast.LENGTH_LONG).show()
                }

            }
        })

        /*viewModel.detailLiveData.observe(viewLifecycleOwner) {
            binding.tvDetailTitle.setText(it.original_title)
            binding.tvLenguage.setText(it.original_language)
            binding.tvOverview.setText(it.overview)
            binding.tvRating.setText(it.popularity.toString())
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}