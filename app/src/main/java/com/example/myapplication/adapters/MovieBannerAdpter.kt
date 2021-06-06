package com.example.myapplication.adapters

import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.ResultsVO
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class MovieBannerAdpter: BaseBannerAdapter<ResultsVO>() {

    override fun bindData(
        holder: BaseViewHolder<ResultsVO>,
        data: ResultsVO?,
        position: Int,
        pageSize: Int
    ) {

        val url = "https://image.tmdb.org/t/p/w500/"+ data!!.backdrop_path
        //holder.setImageResource(R.id.banner_image, url.toInt())
        Glide.with(holder.itemView.context).load(url)
            .into(holder.findViewById(R.id.banner_image))
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_layout_item_view
    }
}