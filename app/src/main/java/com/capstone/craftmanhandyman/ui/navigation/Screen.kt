package com.capstone.craftmanhandyman.ui.navigation

sealed class Screen(val route: String) {
    object Chat : Screen("chat")
    object ChatMessage : Screen("chatMessage")
    object History : Screen("history")
    object PesananSelesai : Screen("pesananSelesai")
    object PesananProses : Screen("pesananProses")
    object HistoryInProfile : Screen("historyInProfile")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
}