package com.capstone.craftman.data.fake

import com.capstone.craftman.R

data class Craftmans(
    val name : String,
    val photoUrl : Int,
    val job : String,
    val location : String,
    val price : Int,
    val stars : Int,
)

object FakeCraftman {
    val dummyCraftmans= listOf(
        Craftmans("Andi", R.drawable.ricky_harun, "Tukang Ac", "Yogyakarta, Sleman", 3000_000, 5),
        Craftmans("Kevin", R.drawable.ricky_harun, "Tukang Listrik", "Yogyakarta, Sleman", 3000_000,4),
        Craftmans("Agus", R.drawable.ricky_harun, "Teknisi Tv", "Yogyakarta, Sleman", 3000_000,3),
    )
}