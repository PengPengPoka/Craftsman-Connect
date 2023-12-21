package com.capstone.craftmanhandyman.view.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.craftmanhandyman.data.preference.UserModel
import com.capstone.craftmanhandyman.data.repository.CraftmanRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: CraftmanRepository): ViewModel() {


    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }


}