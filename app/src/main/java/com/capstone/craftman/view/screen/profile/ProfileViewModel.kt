package com.capstone.craftman.view.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.fake.History
import com.capstone.craftman.data.repository.CraftmanRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: CraftmanRepository): ViewModel() {

    private val _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>> = _historyList

    fun fetchHistory() {

        val HistoryData = repository.getHistory()
        _historyList.value = HistoryData
    }

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }

}