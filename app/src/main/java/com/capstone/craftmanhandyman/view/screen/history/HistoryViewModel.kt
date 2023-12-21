package com.capstone.craftmanhandyman.view.screen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.craftmanhandyman.data.fake.History
import com.capstone.craftmanhandyman.data.repository.CraftmanRepository

class HistoryViewModel(private val repository: CraftmanRepository): ViewModel() {

    private val _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>> = _historyList

    fun fetchHistory() {

        val HistoryData = repository.getHistory()
        _historyList.value = HistoryData
    }

}