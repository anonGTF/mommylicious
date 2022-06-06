package com.mommylicious.mobile.ui.nutrition

import android.view.LayoutInflater
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityDetailMenuBinding

class DetailMenuActivity : BaseActivity<ActivityDetailMenuBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityDetailMenuBinding =
        ActivityDetailMenuBinding::inflate

    override fun setup() {

    }
}