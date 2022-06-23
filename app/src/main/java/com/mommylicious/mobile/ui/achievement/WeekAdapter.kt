package com.mommylicious.mobile.ui.achievement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseAdapter
import com.mommylicious.mobile.data.model.WeeklyAchievement
import com.mommylicious.mobile.databinding.ItemWeekBinding

class WeekAdapter : BaseAdapter<ItemWeekBinding, WeeklyAchievement>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemWeekBinding =
        ItemWeekBinding::inflate

    override fun bind(binding: ItemWeekBinding, item: WeeklyAchievement) {
        with(binding) {
            tvTitle.text = item.title
            val isCompleted = item.taskList.fold(true) { acc, task ->
                var next = false
                if (task.isCompleted && acc) next = true
                next
            }
            val drawable = if (isCompleted) R.drawable.ic_completed else R.drawable.ic_uncompleted
            imgStatus.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, drawable))
        }
    }
}