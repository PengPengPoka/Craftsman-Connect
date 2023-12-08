package com.capstone.craftman.data.fake

import com.capstone.craftman.R

data class Craftmans(
    val name : String,
    val photoUrl : Int,
    val job : String,
    val location : String,
)

object FakeCraftman {
    val dummyCraftmans= listOf(
        Craftmans("Andi", R.drawable.ricky_harun, "Tukang Ac", "Yogyakarta, Sleman"),
        Craftmans("Kevin", R.drawable.ricky_harun, "Tukang Listrik", "Yogyakarta, Sleman"),
        Craftmans("Agus", R.drawable.ricky_harun, "Teknisi Tv", "Yogyakarta, Sleman"),
    )
}