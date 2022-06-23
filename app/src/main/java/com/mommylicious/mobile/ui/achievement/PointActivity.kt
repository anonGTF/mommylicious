package com.mommylicious.mobile.ui.achievement

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.data.model.Task
import com.mommylicious.mobile.data.model.WeeklyAchievement
import com.mommylicious.mobile.databinding.ActivityPointBinding

class PointActivity : BaseActivity<ActivityPointBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPointBinding =
        ActivityPointBinding::inflate

    private lateinit var weekAdapter : WeekAdapter

    override fun setup() {
        setupRecyclerView()
        val taksList = arrayListOf(
            Task("Memenuhi karbohidrat", true),
            Task("Memenuhi protein", false),
            Task("Memenuhi cairan", true),
            Task("Mengisi record", false),
            Task("Membaca artikel", true),
        )
        val completedTaksList = arrayListOf(
            Task("Memenuhi karbohidrat", true),
            Task("Memenuhi protein", true),
            Task("Memenuhi cairan", true),
            Task("Mengisi record", true),
            Task("Membaca artikel", true),
        )
        val dummyData = listOf(
            WeeklyAchievement("Minggu 1", completedTaksList),
            WeeklyAchievement("Minggu 2", taksList),
            WeeklyAchievement("Minggu 3", taksList),
            WeeklyAchievement("Minggu 4", taksList),
        )

        weekAdapter.differ.submitList(dummyData)
    }

    private fun setupRecyclerView() {
        weekAdapter = WeekAdapter()
        with(binding.rvWeek) {
            adapter = weekAdapter
            layoutManager = LinearLayoutManager(context)
        }

        weekAdapter.setOnItemClickListener {
            AchievementListActivity.startActivity(this)
        }
    }

    companion object {
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, PointActivity::class.java))
        }
    }
}