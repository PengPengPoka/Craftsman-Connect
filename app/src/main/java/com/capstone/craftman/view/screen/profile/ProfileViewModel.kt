package com.capstone.craftman.view.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.repository.CraftmanRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: CraftmanRepository): ViewModel() {

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }

}