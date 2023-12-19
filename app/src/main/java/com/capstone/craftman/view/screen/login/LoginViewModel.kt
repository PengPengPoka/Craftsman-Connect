package com.capstone.craftman.view.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.preference.UserModel
import com.capstone.craftman.data.repository.CraftmanRepository
import com.capstone.craftman.helper.UiState
import com.capstone.craftman.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: CraftmanRepository): ViewModel() {
    private val _upload = MutableLiveData<UiState<LoginResponse>>()
    val upload: LiveData<UiState<LoginResponse>> = _upload

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String,) {
        viewModelScope.launch {
            repository.login(email, password).asFlow().collect {
                _upload.value = it
            }
        }
    }

}