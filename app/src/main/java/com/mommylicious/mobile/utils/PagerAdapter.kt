package com.mommylicious.mobile.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(
    fm: FragmentManager,
    private val titles: Array<String>,
    private val fragments: Array<Fragment>
    ) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }
}