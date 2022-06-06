package com.mommylicious.mobile.ui.auth

import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {
    fun login(email: String, password: String) = callApiReturnLiveData(
        apiCall = { userRepository.login(email, password) }
    )

    fun register(name: String, nik: String, email: String, password: String) = callApiReturnLiveData(
        apiCall = { userRepository.register(name, nik, email, password) }
    )

    fun validateIsLoggedIn() = userRepository.isLoggedIn()
}