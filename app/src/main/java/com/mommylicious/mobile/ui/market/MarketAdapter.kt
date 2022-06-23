package com.mommylicious.mobile.ui.market

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.MarketItem
import com.mommylicious.mobile.databinding.ItemShopBinding
import com.squareup.picasso.Picasso

class MarketAdapter : BaseAdapter<ItemShopBinding, MarketItem>(){
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemShopBinding =
        ItemShopBinding::inflate

    override fun bind(binding: ItemShopBinding, item: MarketItem) {
        with(binding) {
            tvTitle.text = item.title
            tvXp.text = item.xp
            Picasso.get().load(item.img).into(imgProgram)
        }
    }
}