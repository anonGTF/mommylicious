package com.mommylicious.mobile.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.Need
import com.mommylicious.mobile.databinding.ItemNeedBinding
import com.squareup.picasso.Picasso

class NeedAdapter : BaseAdapter<ItemNeedBinding, Need>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemNeedBinding =
        ItemNeedBinding::inflate

    override fun bind(binding: ItemNeedBinding, item: Need) {
        with(binding) {
            tvNeedTitle.text = item.name
            tvNeedAmount.text = item.amount
            Picasso.get().load(item.icon).into(imgNeed)
        }
    }
}