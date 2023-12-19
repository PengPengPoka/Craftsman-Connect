package com.capstone.craftman.view.screen.register

import androidx.lifecycle.ViewModel
import com.capstone.craftman.data.repository.CraftmanRepository

class RegisterViewModel(private val repository: CraftmanRepository): ViewModel() {

    fun register(name: String, email: String, noHp: String, password: String) = repository.register(name, email,noHp, password)

}