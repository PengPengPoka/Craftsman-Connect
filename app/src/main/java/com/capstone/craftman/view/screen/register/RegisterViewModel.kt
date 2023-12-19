package com.capstone.craftman.view.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.repository.CraftmanRepository
import com.capstone.craftman.helper.UiState
import com.capstone.craftman.response.LoginResponse
import com.capstone.craftman.response.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: CraftmanRepository): ViewModel() {

    private val _upload = MutableLiveData<UiState<RegisterResponse>>()
    val upload: LiveData<UiState<RegisterResponse>> = _upload

    fun register(nama : String, email: String, noHp : String, password: String) {
        viewModelScope.launch {
            repository.register(nama, email, noHp, password).asFlow().collect {
                _upload.value = it
            }
        }
    }

}