package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.data.vos.Movie
import com.example.myapplication.viewholders.MovieViewHolder

class MovieListAdapter(val onClick: (Movie)-> Unit) : ListAdapter<Movie, MovieViewHolder>(MovieDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view,parent,false)

        return MovieViewHolder(itemView,onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
         val data = getItem(position)
        holder.bind(data)
    }
}