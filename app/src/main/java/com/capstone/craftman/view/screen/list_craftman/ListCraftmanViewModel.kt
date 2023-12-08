package com.capstone.craftman.view.screen.list_craftman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.craftman.data.fake.Craftmans
import com.capstone.craftman.data.repository.CraftmanRepository

class ListCraftmanViewModel(private val repository: CraftmanRepository) : ViewModel() {
    private val _CraftmanList = MutableLiveData<List<Craftmans>>()
    val CraftmanList: LiveData<List<Craftmans>> = _CraftmanList

    fun fetchCraftman() {

        val CraftmanData = repository.getCraftman()
        _CraftmanList.value = CraftmanData
    }
}