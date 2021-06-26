package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.myapplication.R
import com.example.myapplication.data.vos.Movie
import com.example.myapplication.viewholders.MovieViewHolder

class TopRatedListPagingAdapter(val onClick: (Movie)-> Unit): PagingDataAdapter<Movie,MovieViewHolder>(MovieDiff) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.movie_view,parent,false)
        return MovieViewHolder(itemView,onClick)
    }
}