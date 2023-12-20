package com.capstone.craftman.response

import com.google.gson.annotations.SerializedName

data class CraftmanResponse (
    @field:SerializedName("error")
    val success: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("tukangList")
    val craftmanList: List<CraftmanList>
)

data class CraftmanList (
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("nama")
    val nama: String,
    @field:SerializedName("layanan")
    val layanan: String,
    @field:SerializedName("alamat")
    val alamat: String,
    @field:SerializedName("deskripsi")
    val deskripsi: String,
    @field:SerializedName("telepon")
    val telepon: String
)
