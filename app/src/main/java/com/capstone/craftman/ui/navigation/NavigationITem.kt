package com.capstone.craftman.ui.navigation

import androidx.compose.ui.graphics.ImageBitmap

data class NavigationItem(
    val title: String,
    val icon: ImageBitmap,
    val screen: Screen
)