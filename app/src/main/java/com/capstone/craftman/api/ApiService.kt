package com.capstone.craftman.api

import com.capstone.craftman.response.CraftmanResponse
import com.capstone.craftman.response.LoginRequest
import com.capstone.craftman.response.LoginResponse
import com.capstone.craftman.response.RegisterRequest
import com.capstone.craftman.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(@Body requestBody: RegisterRequest): RegisterResponse

    @POST("login")
    suspend fun login(@Body requestBody: LoginRequest): LoginResponse

    @GET("tukang")
    suspend fun getCraftman(): CraftmanResponse
}