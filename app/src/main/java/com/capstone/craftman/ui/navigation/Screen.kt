package com.capstone.craftman.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chat : Screen("chat")
    object History : Screen("history")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
}