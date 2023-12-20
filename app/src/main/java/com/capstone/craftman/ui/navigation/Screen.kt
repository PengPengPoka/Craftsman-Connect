package com.capstone.craftman.ui.navigation

sealed class Screen(val route: String) {
    object ListCraftman : Screen("listCraftman")
    object Home : Screen("home")
    object Chat : Screen("chat")
    object ChatMessage : Screen("chatMessage")
    object History : Screen("history")
    object Feedback : Screen("feedback")
    object PesananSelesai : Screen("pesananSelesai")
    object PesananProses : Screen("pesananProses")
    object HistoryInProfile : Screen("historyInProfile")
    object Profile : Screen("profile")
    object DetailCraftman : Screen("listCraftman/{name}/{layanan}/{deskripsi}") {
        fun createRoute(name: String, layanan: String, deskripsi: String) =
            "listCraftman/$name/$layanan/$deskripsi"
    }
    object Login : Screen("login")
    object Register : Screen("register")
}