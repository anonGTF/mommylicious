package com.mommylicious.mobile.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseFragment
import com.mommylicious.mobile.databinding.FragmentMenuBinding
import com.mommylicious.mobile.utils.MENU_TAB_TITLES
import com.mommylicious.mobile.utils.PagerAdapter
import com.mommylicious.mobile.utils.TabEntity

class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMenuBinding =
        FragmentMenuBinding::inflate

    override fun setup() {
        if (activity != null) {
            val titles = mutableListOf<String>()
            val fragments = mutableListOf<Fragment>()
            val tabData = arrayListOf<CustomTabEntity>()

            MENU_TAB_TITLES.forEach {
                tabData.add(TabEntity(getString(it), R.drawable.ic_add, R.drawable.ic_add))
                titles.add(getString(it))
                fragments.add(MenuListFragment(getString(it)))
            }

            binding.viewPager.adapter = PagerAdapter(parentFragmentManager,
                titles.toTypedArray(), fragments.toTypedArray())
            binding.tabs.setTabData(tabData)
            binding.tabs.setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    binding.viewPager.currentItem = position
                }

                override fun onTabReselect(position: Int) {}
            })

            binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
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
        }
    }
}