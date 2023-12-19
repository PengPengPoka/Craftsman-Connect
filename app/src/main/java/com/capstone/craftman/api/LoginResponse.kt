package com.capstone.craftman.api

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("token")
    val token: String
)
