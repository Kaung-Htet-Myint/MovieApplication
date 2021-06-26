package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.data.vos.Movie
import com.example.myapplication.viewholders.SearchViewHolder

class SearchResultListAdapter(val onClick: (Movie)-> Unit) : ListAdapter<Movie, SearchViewHolder>(MovieDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_result_list_view,parent,false)

        return SearchViewHolder(itemView,onClick)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}