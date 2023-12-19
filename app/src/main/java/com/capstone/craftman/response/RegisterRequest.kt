package com.capstone.craftman.response

data class RegisterRequest(
    val nama: String,
    val email: String,
    val noHp : String,
    val password: String
)