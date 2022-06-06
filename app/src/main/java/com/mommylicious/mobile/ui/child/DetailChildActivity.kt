package com.mommylicious.mobile.ui.child

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityDetailChildBinding

class DetailChildActivity : BaseActivity<ActivityDetailChildBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityDetailChildBinding =
        ActivityDetailChildBinding::inflate

    override fun setup() {

    }
}