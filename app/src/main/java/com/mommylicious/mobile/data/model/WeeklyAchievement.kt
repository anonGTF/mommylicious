package com.mommylicious.mobile.data.model

import com.mommylicious.mobile.base.BaseModel

data class WeeklyAchievement(
    val title: String,
    val taskList: List<Task>
) : BaseModel(title)