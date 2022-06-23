package com.mommylicious.mobile.utils

import com.flyco.tablayout.listener.CustomTabEntity

data class TabEntity(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
) : CustomTabEntity {
    override fun getTabTitle(): String = title

    override fun getTabSelectedIcon(): Int = selectedIcon

    override fun getTabUnselectedIcon(): Int = unselectedIcon
}
