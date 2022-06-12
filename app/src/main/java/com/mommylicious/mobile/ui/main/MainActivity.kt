package com.mommylicious.mobile.ui.main

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.mommylicious.mobile.R
import com.mommylicious.mobile.base.BaseActivity
import com.mommylicious.mobile.databinding.ActivityMainBinding
import com.mommylicious.mobile.ui.home.HomeFragment
import com.mommylicious.mobile.ui.menu.NutritionListFragment
import com.mommylicious.mobile.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        = ActivityMainBinding::inflate
    private var shouldGoTo: Int = 0
    private val homeFragment = HomeFragment()
    private val nutritionListFragment = NutritionListFragment()
    private val profileFragment = ProfileFragment()

    override fun setup() {
        getDataFromBundle()
        setInitialFragment()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miHome -> setCurrentFragment(homeFragment)
                R.id.miNutrition -> setCurrentFragment(nutritionListFragment)
                R.id.miProfile -> setCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun setInitialFragment() {
        when (shouldGoTo) {
            ID_GO_TO_PROFILE -> {
                setCurrentFragment(profileFragment)
                binding.bottomNavigationView.selectedItemId = R.id.miProfile
            }
            else -> setCurrentFragment(homeFragment)
        }
    }

    private fun getDataFromBundle() {
        val bundle = intent.extras
        if (bundle != null) {
            shouldGoTo = intent.getIntExtra(EXTRA_DATA, 0)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val ID_GO_TO_PROFILE = 1
    }
}