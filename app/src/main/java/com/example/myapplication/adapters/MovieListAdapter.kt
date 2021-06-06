package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.viewholders.MovieViewHolder

class MovieListAdapter(val onClick: (ResultsVO)-> Unit) : RecyclerView.Adapter<MovieViewHolder>() {
    var movieList: List<ResultsVO> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_view,parent,false)

        return MovieViewHolder(itemView,onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
         val data = movieList[position]
         val url = "https://image.tmdb.org/t/p/w500/"+data.backdrop_path
        holder.itemView.findViewById<TextView>(R.id.tvMovieName).setText(data.original_title)
        Glide.with(holder.itemView.context).load(url)
            .into(holder.itemView.findViewById(R.id.ivMovie))

        holder.itemView.setOnClickListener {
            onClick(data)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}