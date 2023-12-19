package com.capstone.craftman.view.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.capstone.craftman.data.preference.UserModel
import com.capstone.craftman.data.repository.CraftmanRepository
import com.capstone.craftman.helper.UiState
import com.capstone.craftman.response.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: CraftmanRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _upload = MutableLiveData<UiState<RegisterResponse>>()
    val upload: LiveData<UiState<RegisterResponse>> = _upload
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun uploadData(nama: String, email: String, noHp : String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.postValue(true) // Set loading to true

                repository.registerAccount(nama, email, noHp, password).asFlow().collect {
                    _upload.value = it
                }
            } finally {
                _isLoading.postValue(false) // Set loading to false
            }
        }
    }

}