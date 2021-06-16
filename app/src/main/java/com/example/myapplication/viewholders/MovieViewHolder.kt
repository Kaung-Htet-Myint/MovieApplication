package com.example.myapplication.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.persistance.entities.MovieEntity

class MovieViewHolder(itemView: View, val onClick: (MovieEntity) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(data: MovieEntity){
        val url = "https://image.tmdb.org/t/p/w500/"+data.backdropPath
        Glide.with(itemView.context).load(url)
            .into(itemView.findViewById(R.id.ivMovie))

        itemView.findViewById<TextView>(R.id.tvMovieName).setText(data.originalTitle)
        itemView.setOnClickListener {
            onClick(data)
        }
    }
}