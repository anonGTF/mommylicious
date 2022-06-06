package com.mommylicious.mobile.ui.achievement

import android.view.LayoutInflater
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityAchievementListBinding

class AchievementListActivity : BaseActivity<ActivityAchievementListBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAchievementListBinding =
        ActivityAchievementListBinding::inflate

    override fun setup() {

    }
}