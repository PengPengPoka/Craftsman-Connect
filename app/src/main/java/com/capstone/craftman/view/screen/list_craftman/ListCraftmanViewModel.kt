package com.capstone.craftman.view.screen.list_craftman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.fake.Craftmans
import com.capstone.craftman.data.repository.CraftmanRepository
import com.capstone.craftman.helper.UiState
import com.capstone.craftman.response.CraftmanList
import com.capstone.craftman.response.CraftmanResponse
import kotlinx.coroutines.launch

class ListCraftmanViewModel(private val repository: CraftmanRepository) : ViewModel() {
    private val _TukangList = MutableLiveData<UiState<CraftmanResponse>>()
    val TukangList: LiveData<UiState<CraftmanResponse>> = _TukangList

    init {
        getTukang()
    }
    fun getTukang(){
        viewModelScope.launch {
            repository.getTukang().asFlow().collect(){
                _TukangList.value = it
            }
        }
    }

}