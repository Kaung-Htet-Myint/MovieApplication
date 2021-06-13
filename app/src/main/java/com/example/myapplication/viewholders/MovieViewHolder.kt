package com.example.myapplication.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.MovieVO
import com.example.myapplication.data.vos.ResultsVO

class MovieViewHolder(itemView: View, val onClick: (ResultsVO) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(data: ResultsVO){
        val url = "https://image.tmdb.org/t/p/w500/"+data.poster_path
        Glide.with(itemView.context).load(url)
            .into(itemView.findViewById(R.id.ivMovie))

        itemView.findViewById<TextView>(R.id.tvMovieName).setText(data.original_title)
        itemView.setOnClickListener {
            onClick(data)
        }
    }
}