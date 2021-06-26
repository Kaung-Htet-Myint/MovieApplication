package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.data.vos.GenresVO
import com.example.myapplication.viewholders.FilterViewHolder

class FilterAdapter (val onClick: (GenresVO)-> Unit) : ListAdapter<GenresVO, FilterViewHolder>(FilterDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.filter_item_view, parent, false)

        return FilterViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

}


object FilterDiff : DiffUtil.ItemCallback<GenresVO>() {
    override fun areItemsTheSame(oldItem: GenresVO, newItem: GenresVO): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GenresVO, newItem: GenresVO): Boolean {
        return oldItem == newItem
    }
}