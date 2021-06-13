package com.example.myapplication.adapters

import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.vos.TrendingResultVO
import com.example.myapplication.domain.Trending
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class MovieBannerAdpter: BaseBannerAdapter<Trending>() {

    override fun bindData(
        holder: BaseViewHolder<Trending>?,
        data: Trending?,
        position: Int,
        pageSize: Int
    ) {

        val url = "https://image.tmdb.org/t/p/w500/"+ data!!.getImage()
        Glide.with(holder!!.itemView.context).load(url)
            .into(holder.findViewById(R.id.banner_image))
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_layout_item_view
    }


}