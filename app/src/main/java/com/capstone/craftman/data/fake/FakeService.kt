package com.capstone.craftman.data.fake

import com.capstone.craftman.R

data class FakeService(
    val image : Int,
    val title : String,
)

val dummyService = listOf(
    FakeService(R.drawable.ic_sapu, "Tukang bersih 1"),
    FakeService(R.drawable.ic_grass, "Tukang kebun"),
    FakeService(R.drawable.ic_tkgac, "Tukang Ac "),
    FakeService(R.drawable.ic_tkgbangunan, "Tukang Bangunan "),
    FakeService(R.drawable.ic_tkgkunci, "Tukang Kunci"),
    FakeService(R.drawable.ic_electric, "Tukang Listrik"),
)
