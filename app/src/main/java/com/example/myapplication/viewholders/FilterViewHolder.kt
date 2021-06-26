package com.example.myapplication.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.vos.GenresVO

class FilterViewHolder(itemView: View, val onClick: (GenresVO) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bind(data: GenresVO) {
        itemView.findViewById<TextView>(R.id.chipFilter).text = data.name

        itemView.findViewById<TextView>(R.id.chipFilter).setOnClickListener {
            onClick(data)
        }
    }
}