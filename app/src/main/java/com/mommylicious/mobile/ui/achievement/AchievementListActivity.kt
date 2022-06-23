package com.mommylicious.mobile.ui.achievement

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.data.model.Task
import com.mommylicious.mobile.data.model.WeeklyAchievement
import com.mommylicious.mobile.databinding.ActivityAchievementListBinding

class AchievementListActivity : BaseActivity<ActivityAchievementListBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAchievementListBinding =
        ActivityAchievementListBinding::inflate

    override fun setup() {
        val taksList = arrayListOf(
            Task("Memenuhi karbohidrat", true),
            Task("Memenuhi protein", false),
            Task("Memenuhi cairan", true),
            Task("Mengisi record", false),
            Task("Membaca artikel", true),
        )
        val adapter = AchievementAdapter(listOf(
            WeeklyAchievement("Senin", taksList),
            WeeklyAchievement("Selasa", taksList),
            WeeklyAchievement("Rabu", taksList),
            WeeklyAchievement("Kamis", taksList),
            WeeklyAchievement("Jumat", taksList),
            WeeklyAchievement("Sabtu", taksList),
            WeeklyAchievement("Minggu", taksList),
        ))
        binding.accordianView.adapter = adapter
        binding.accordianView.updatePosition(3)
        Log.d("coba", "setup: ${adapter.getItemCount()}")
    }

    companion object {
        fun startActivity(ctx: Context) {
            ctx.startActivity(Intent(ctx, AchievementListActivity::class.java))
        }
    }
}