package com.capstone.craftmanhandyman.data.fake

import com.capstone.craftmanhandyman.R

data class FakeService(
    val image : Int,
    val title : String,
)

val dummyService = listOf(
    FakeService(R.drawable.ic_sapu, "Cleaning Service"),
    FakeService(R.drawable.ic_grass, "Tukang kebun"),
    FakeService(R.drawable.ic_tkgac, "Tukang Ac "),
    FakeService(R.drawable.ic_tkgbangunan, "Tukang Bangunan "),
    FakeService(R.drawable.ic_electric, "Tukang Listrik"),
)
