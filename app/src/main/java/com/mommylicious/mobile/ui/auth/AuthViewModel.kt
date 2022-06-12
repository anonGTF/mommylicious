package com.mommylicious.mobile.ui.auth

import android.util.Log
import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.ChildRepository
import com.mommylicious.mobile.data.firebase.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val childRepository: ChildRepository
): BaseViewModel() {
    fun login(email: String, password: String) = callApiReturnLiveData(
        apiCall = { userRepository.login(email, password) }
    )

    fun register(name: String, nik: String, email: String, password: String) = callApiReturnLiveData(
        apiCall = { userRepository.register(name, nik, email, password) }
    )

    fun validateIsLoggedIn() = userRepository.isLoggedIn()

    fun getSelectedChildId() = childRepository.getChildId()

    fun saveYoungestChildId() = callApiReturnLiveData(
        apiCall = {
            childRepository.getChildren()
        },
        handleBeforePostSuccess = {
            Log.d("coba", "saveYoungestChildId: $it")
            childRepository.saveChildId(it?.get(0)?.childId.orEmpty())
        }
    )
}