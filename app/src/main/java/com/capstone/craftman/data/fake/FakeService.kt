package com.capstone.craftman.data.fake

import com.capstone.craftman.R

data class FakeService(
    val image : Int,
    val title : String,
)

val dummyService = listOf(
    FakeService(R.drawable.ic_sapu, "Tukang bersih 1"),
    FakeService(R.drawable.ic_sapu, "Tukang bersih 2"),
    FakeService(R.drawable.ic_sapu, "Tukang bersih 3 "),
    FakeService(R.drawable.ic_sapu, "Tukang bersih 4 "),
)
