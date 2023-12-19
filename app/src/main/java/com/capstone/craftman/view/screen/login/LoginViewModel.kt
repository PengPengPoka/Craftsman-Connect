package com.capstone.craftman.view.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.preference.UserModel
import com.capstone.craftman.data.repository.CraftmanRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: CraftmanRepository): ViewModel() {

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) = repository.login(email, password)

}