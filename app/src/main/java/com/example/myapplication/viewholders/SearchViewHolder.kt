package com.example.myapplication.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.Movie

class SearchViewHolder(itemView: View, val onClick: (Movie) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Movie) {
        val url = "https://image.tmdb.org/t/p/w500/" + data.backdropPath
        Glide.with(itemView.context).load(url)
            .into(itemView.findViewById(R.id.ivSearch))

        itemView.findViewById<TextView>(R.id.tvSearchMovieTitle).setText(data.originalTitle)
        itemView.findViewById<TextView>(R.id.tvOverview).setText(data.overview)
        itemView.setOnClickListener {
            onClick(data)
        }
    }
}