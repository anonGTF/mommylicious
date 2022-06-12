package com.mommylicious.mobile.ui.child

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityDetailChildBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailChildActivity : BaseActivity<ActivityDetailChildBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityDetailChildBinding =
        ActivityDetailChildBinding::inflate

    private var id = ""

    override fun setup() {
        id = intent.getStringExtra(EXTRA_DATA).orEmpty()
    }

    companion object {
        const val EXTRA_DATA = "data"
        fun startActivity(ctx: Context, id: String) {
            val intent = Intent(ctx, DetailChildActivity::class.java)
            intent.putExtra(EXTRA_DATA, id)
            ctx.startActivity(intent)
        }
    }
}