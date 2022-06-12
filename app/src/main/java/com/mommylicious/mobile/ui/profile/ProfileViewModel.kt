package com.mommylicious.mobile.ui.profile

import android.net.Uri
import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.ChildRepository
import com.mommylicious.mobile.data.firebase.UserRepository
import com.mommylicious.mobile.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val childRepository: ChildRepository
) : BaseViewModel() {
    fun getUserEmail() = userRepository.getUserEmail()

    fun getUserId() = userRepository.getUserId()

    fun getProfile() = callApiReturnLiveData(
        apiCall = { userRepository.getProfile() }
    )

//    fun uploadImageProfile(data: Uri) = callApiReturnLiveData(
//        apiCall = { userRepository.uploadProfileImage(data) }
//    )
//
//    fun saveProfile(name: String, birthDate: String, location: String, gender: Boolean, photoProfile: String)
//        = callApiReturnLiveData(
//        apiCall = {
//            val user = User(getUserId(), name, birthDate, location, gender, photoProfile)
//            userRepository.saveProfile(user)
//        }
//    )

    fun getChildren() = callApiReturnLiveData(
        apiCall = { childRepository.getChildren() }
    )

    fun logout() = callApiReturnLiveData(
        apiCall = { userRepository.logout() }
    )
}