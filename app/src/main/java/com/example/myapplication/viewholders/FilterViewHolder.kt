package com.example.myapplication.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.Genres

class FilterViewHolder(itemView: View, val onClick: (Genres) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Genres) {
        itemView.findViewById<TextView>(R.id.chipFilter).text = data.name

        itemView.findViewById<TextView>(R.id.chipFilter).setOnClickListener {
            onClick(data)
        }
    }
}