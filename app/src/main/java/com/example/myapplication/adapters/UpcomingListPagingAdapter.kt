package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.R
import com.example.myapplication.data.vos.ResultsVO
import com.example.myapplication.viewholders.MovieViewHolder

class UpcomingListPagingAdapter(val onClick: (ResultsVO)-> Unit): PagingDataAdapter<ResultsVO, MovieViewHolder>(MovieDiff) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_view,parent,false)
        return  MovieViewHolder(itemView,onClick)
    }
}

object MovieDiff : DiffUtil.ItemCallback<ResultsVO>() {
    override fun areItemsTheSame(oldItem: ResultsVO, newItem: ResultsVO): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultsVO, newItem: ResultsVO): Boolean {
        return oldItem == newItem
    }
}