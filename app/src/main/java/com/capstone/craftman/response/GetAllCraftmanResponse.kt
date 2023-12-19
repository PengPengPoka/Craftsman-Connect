package com.capstone.craftman.response

import com.google.gson.annotations.SerializedName

data class GetAllCraftmanResponse(

	@field:SerializedName("tukangList")
	val tukangList: List<TukangListItem>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class TukangListItem(

	@field:SerializedName("layanan")
	val layanan: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("telepon")
	val telepon: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("alamat")
	val alamat: String
)
