package com.capstone.craftman.api

import com.google.gson.annotations.SerializedName

data class TukangRespoonse (
    @field:SerializedName("success")
    val success: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("tukangList")
    val tukangList: List<TukangList>
)

data class TukangList(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("nama")
    val nama: String,
    @field:SerializedName("layanan")
    val layanan: String,
    @field:SerializedName("deskripsi")
    val deskripsi: String,
    @field:SerializedName("telepon")
    val telepon: String
)