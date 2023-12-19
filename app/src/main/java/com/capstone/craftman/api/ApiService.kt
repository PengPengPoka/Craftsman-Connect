package com.capstone.craftman.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("nama") nama : String,
        @Field("email") email : String,
        @Field("noHp") noHp : String,
        @Field("password") password : String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ): LoginResponse

    @GET("tukang")
    suspend fun getTukang(): TukangRespoonse
}