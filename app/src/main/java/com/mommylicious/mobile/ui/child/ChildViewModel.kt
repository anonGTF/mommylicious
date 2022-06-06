package com.mommylicious.mobile.ui.child

import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.ChildRepository
import com.mommylicious.mobile.data.model.Child
import com.mommylicious.mobile.utils.DEFAULT_BABY_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChildViewModel @Inject constructor(
    private val childRepository: ChildRepository
): BaseViewModel() {

    fun addChild(name: String, birthDate: String, gender: String, weekOfBirth: Int) =
        callApiReturnLiveData (
           apiCall = {
               childRepository.add(Child("", name, birthDate, gender, weekOfBirth, DEFAULT_BABY_IMAGE))
           }
        )

    fun getChildId() = childRepository.getChildId()

    fun saveChildId(id: String) = childRepository.saveChildId(id)
}