package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.data.vos.TrendingResultVO
import com.example.myapplication.domain.Trending
import com.example.myapplication.viewholders.TrendingViewHolder

class TrendingListAdapter(val onClick: (Trending) -> Unit): ListAdapter<Trending,TrendingViewHolder>(TrendingDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view,parent,false)
        return TrendingViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}

object TrendingDiff : DiffUtil.ItemCallback<Trending>() {
    override fun areItemsTheSame(oldItem: Trending, newItem: Trending): Boolean {
        // Id is unique.
        return oldItem.getIdentifier() == newItem.getIdentifier()
    }

    override fun areContentsTheSame(oldItem: Trending, newItem: Trending): Boolean {
        return oldItem == newItem
    }
}
