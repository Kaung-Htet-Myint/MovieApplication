package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.data.model.MovieModel
import com.example.myapplication.data.model.MovieModelImpl
import com.example.myapplication.databinding.FragmentMovieDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val args by navArgs<MovieDetailFragmentArgs>()
    lateinit var movieModel: MovieModel
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

        movieModel = MovieModelImpl()
        movieModel.getMovieDetail(args.id,
        onSuccess = {
            val url = "https://image.tmdb.org/t/p/original/"+it.backdrop_path
            Glide.with(requireContext()).load(url)
                .into(binding.ivMovieDetail)

            binding.tvDetailTitle.setText(it.original_title)
            binding.tvLenguage.setText(it.original_language)
            binding.tvOverview.setText(it.overview)
            binding.tvRating.setText(it.popularity.toString())
        },
        onFailure = {

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}