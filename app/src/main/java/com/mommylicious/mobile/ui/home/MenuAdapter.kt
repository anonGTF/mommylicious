package com.mommylicious.mobile.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.Menu
import com.mommylicious.mobile.databinding.ItemMenuBinding
import com.squareup.picasso.Picasso

class MenuAdapter : BaseAdapter<ItemMenuBinding, Menu>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemMenuBinding =
        ItemMenuBinding::inflate

    override fun bind(binding: ItemMenuBinding, item: Menu) {
        with(binding) {
            tvTitle.text = item.name
            tvDescription.text = buildString {
                append(item.type)
                append(" | ")
                append(item.timeSpent)
                append(" | ")
                append(item.calories)
                append("kCal")
            }
            Picasso.get().load(item.image).into(imgMenu)
        }
    }
}