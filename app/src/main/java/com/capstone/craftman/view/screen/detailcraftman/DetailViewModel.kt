package com.capstone.craftman.view.screen.detailcraftman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.fake.Craftmans
import com.capstone.craftman.data.repository.CraftmanRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CraftmanRepository): ViewModel() {
    private val _craftsman = MutableLiveData<Craftmans>()
    val craftsman: LiveData<Craftmans> get() = _craftsman

    fun getCraftsmanByName(name: String) {
        viewModelScope.launch {
            val craftsmanByName = repository.getCraftmanByName(name)
            _craftsman.value = craftsmanByName!!
        }
    }
}