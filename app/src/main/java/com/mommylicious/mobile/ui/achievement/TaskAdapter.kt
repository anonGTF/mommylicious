package com.mommylicious.mobile.ui.achievement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.Task
import com.mommylicious.mobile.databinding.ItemTaskBinding

class TaskAdapter : BaseAdapter<ItemTaskBinding, Task>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemTaskBinding =
        ItemTaskBinding::inflate

    override fun bind(binding: ItemTaskBinding, item: Task) {
        with(binding) {
            tvName.text = item.name
            val drawable = if (item.isCompleted) R.drawable.ic_completed else R.drawable.ic_uncompleted
            imgStatus.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, drawable))
        }
    }

}