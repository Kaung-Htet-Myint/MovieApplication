package com.example.myapplication.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.TrendingResultVO
import com.example.myapplication.domain.Trending

class TrendingViewHolder(itemView: View,val onClick: (Trending)-> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Trending){
        val url = "https://image.tmdb.org/t/p/w500/"+data.getImage()
        Glide.with(itemView.context).load(url)
            .into(itemView.findViewById(R.id.ivMovie))

        itemView.findViewById<TextView>(R.id.tvMovieName).setText(data.getDisplayName())
        itemView.setOnClickListener {
            onClick(data)
        }
    }
}