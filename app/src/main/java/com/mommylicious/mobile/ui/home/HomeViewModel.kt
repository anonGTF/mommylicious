package com.mommylicious.mobile.ui.home

import com.mommylicious.mobile.base.BaseViewModel
import com.mommylicious.mobile.data.firebase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val recordRepository: RecordRepository,
    private val articleRepository: ArticleRepository,
    private val needRepository: NeedRepository,
    private val menuRepository: MenuRepository
): BaseViewModel() {

    private fun getChildId() = childRepository.getChildId()

    fun getChild() = callApiReturnLiveData(
        apiCall = { childRepository.getChild(getChildId()) }
    )

    fun getLatestRecord() = callApiReturnLiveData(
        apiCall = { recordRepository.getLatestRecord() }
    )

    fun getArticles() = callApiReturnLiveData(
        apiCall = { articleRepository.getArticles() }
    )

    fun getMenu() = callApiReturnLiveData(
        apiCall = { menuRepository.getMenus() }
    )

    fun getNeed() = callApiReturnLiveData(
        apiCall = { needRepository.getNeeds() }
    )
}