package com.mommylicious.mobile.ui.record

import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.RecordRepository
import com.mommylicious.mobile.data.model.Record
import com.mommylicious.mobile.utils.orNow
import com.mommylicious.mobile.utils.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val recordRepository: RecordRepository
): BaseViewModel() {

    fun addRecord(height: Double, weight: Double, head: Double, date: String) = callApiReturnLiveData(
        apiCall = {
            recordRepository.add(Record("", height, weight, head,
                date.toDate().orNow()))
        }
    )
}