package com.mommylicious.mobile.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.Child
import com.mommylicious.mobile.data.model.Child.Companion.getAge
import com.mommylicious.mobile.databinding.ItemChildBinding
import com.squareup.picasso.Picasso

class ChildAdapter : BaseAdapter<ItemChildBinding, Child>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemChildBinding =
        ItemChildBinding::inflate

    override fun bind(binding: ItemChildBinding, item: Child) {
        with(binding) {
            tvName.text = item.name
            tvAge.text = item.getAge()
            Picasso.get().load(item.imageUrl).into(imgProfile)
        }
    }
}