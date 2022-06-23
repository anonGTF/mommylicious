package com.mommylicious.mobile.ui.record

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityRecordBinding
import com.mommylicious.mobile.utils.PagerAdapter
import com.mommylicious.mobile.utils.RECORD_TAB_TITLES
import com.mommylicious.mobile.utils.TabEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordActivity : BaseActivity<ActivityRecordBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecordBinding =
        ActivityRecordBinding::inflate

    private var name = ""
    private var isMale = false

    override fun setup() {
        val titles = mutableListOf<String>()
        val fragments = mutableListOf<Fragment>()
        val tabData = arrayListOf<CustomTabEntity>()
        name = intent.getStringExtra(EXTRA_NAME).orEmpty()
        isMale = intent.getBooleanExtra(EXTRA_GENDER, false)

        RECORD_TAB_TITLES.forEach {
            tabData.add(TabEntity(getString(it), R.drawable.ic_add, R.drawable.ic_add))
            titles.add(getString(it))
            fragments.add(RecordListFragment(getString(it), isMale))
        }

        binding.viewPager.adapter = PagerAdapter(supportFragmentManager,
            titles.toTypedArray(), fragments.toTypedArray())
        binding.tabs.setTabData(tabData)
        binding.tabs.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                binding.viewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {}
        })

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.tabs.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.viewPager.currentItem = 1

        binding.fab.setOnClickListener {
            AddRecordActivity.startActivity(this)
        }

        binding.tvTitle.text = name
    }

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_GENDER = "gender"
        fun startActivity(ctx: Context, name: String, gender: String) {
            val intent = Intent(ctx, RecordActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            intent.putExtra(EXTRA_GENDER, gender == "Laki-laki")
            ctx.startActivity(intent)
        }
    }
}