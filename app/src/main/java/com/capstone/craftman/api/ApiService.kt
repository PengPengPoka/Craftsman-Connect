package com.capstone.craftman.api

import com.capstone.craftman.response.GetAllCraftmanResponse
import com.capstone.craftman.response.LoginRequest
import com.capstone.craftman.response.LoginResponse
import com.capstone.craftman.response.RegisterRequest
import com.capstone.craftman.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(@Body requestBody: RegisterRequest): RegisterResponse
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(@Body requestBody: LoginRequest): LoginResponse

    @GET("categories")
    suspend fun getAllCraftman(): GetAllCraftmanResponse
}