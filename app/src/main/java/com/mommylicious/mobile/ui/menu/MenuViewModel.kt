package com.mommylicious.mobile.ui.menu

import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : BaseViewModel() {

    fun getMenuByType(type: String) = callApiReturnLiveData(
        apiCall = { menuRepository.getMenuByType(type) }
    )

}