package com.capstone.craftman.ui.navigation

sealed class Screen(val route: String) {
    object ListCraftman : Screen("listCraftman")
    object Home : Screen("home")
    object Chat : Screen("chat")
    object History : Screen("history")
    object PesananSelesai : Screen("pesananSelesai")
    object PesananProses : Screen("pesananProses")
    object HistoryInProfile : Screen("historyInProfile")
    object Profile : Screen("profile")
    object DetailCraftman : Screen("listCraftman/{name}") {
        fun createRoute(name: String) = "listCraftman/$name"
    }
    object Login : Screen("login")
    object Register : Screen("register")
}