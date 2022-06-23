package com.mommylicious.mobile.data.model

import com.mommylicious.mobile.base.BaseModel

data class Task(
    val name: String,
    val isCompleted: Boolean
) : BaseModel(name)
