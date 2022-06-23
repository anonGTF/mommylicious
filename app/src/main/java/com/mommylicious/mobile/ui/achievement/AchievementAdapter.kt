package com.mommylicious.mobile.ui.achievement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import beepbeep.accordion_library.AccordionAdapter
import beepbeep.accordion_library.AccordionView
import com.mommylicious.mobile.R
import com.mommylicious.mobile.data.model.WeeklyAchievement
import kotlinx.android.synthetic.main.item_accordion_content.view.*
import kotlinx.android.synthetic.main.item_accordion_title.view.*

class AchievementAdapter(val dataArray: List<WeeklyAchievement>) : AccordionAdapter {
    override fun onCreateViewHolderForTitle(parent: ViewGroup): AccordionView.ViewHolder {
        return TitleViewHolder.create(parent)
    }

    override fun onCreateViewHolderForContent(parent: ViewGroup): AccordionView.ViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewForTitle(viewHolder: AccordionView.ViewHolder, position: Int, arrowDirection: AccordionAdapter.ArrowDirection) {
        val dataModel = dataArray[position]
        (viewHolder as TitleViewHolder).itemView.apply {
            titleTextView.text = dataModel.title
            val drawable = when (arrowDirection) {
                AccordionAdapter.ArrowDirection.UP -> R.drawable.ic_arrow_up
                AccordionAdapter.ArrowDirection.DOWN -> R.drawable.ic_arrow_down
                AccordionAdapter.ArrowDirection.NONE -> R.drawable.ic_remove
            }

            arrow.setImageResource(drawable)
        }
    }

    override fun onBindViewForContent(viewHolder: AccordionView.ViewHolder, position: Int) {
        val dataModel = dataArray[position]
        (viewHolder as ContentViewHolder).itemView.apply {
            val taskAdapter = TaskAdapter()
            rv_task.adapter = taskAdapter
            rv_task.layoutManager = LinearLayoutManager(viewHolder.itemView.context)

            taskAdapter.differ.submitList(dataModel.taskList)
        }
    }

    override fun getItemCount() = dataArray.size
}

class TitleViewHolder(itemView: View) : AccordionView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): TitleViewHolder {
            return TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_accordion_title, parent, false))
        }
    }
}

class ContentViewHolder(itemView: View) : AccordionView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): ContentViewHolder {
            return ContentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_accordion_content, parent, false))
        }
    }
}