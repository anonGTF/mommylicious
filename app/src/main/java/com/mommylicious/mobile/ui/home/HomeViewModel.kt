package com.mommylicious.mobile.ui.home

import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.ChildRepository
import com.mommylicious.mobile.data.firebase.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val recordRepository: RecordRepository
): BaseViewModel() {

    private fun getChildId() = childRepository.getChildId()

    fun getChild() = callApiReturnLiveData(
        apiCall = { childRepository.getChild(getChildId()) }
    )

    fun getLatestRecord() = callApiReturnLiveData(
        apiCall = { recordRepository.getLatestRecord() }
    )
}