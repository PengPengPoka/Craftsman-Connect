package com.capstone.craftmanhandyman.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.view.screen.chat.ChatMessageScreen
import com.capstone.craftmanhandyman.ui.component.BottomBar
import com.capstone.craftmanhandyman.ui.navigation.Screen
import com.capstone.craftmanhandyman.view.screen.chat.ChatScreen
import com.capstone.craftmanhandyman.view.screen.history.HistoryScreen
import com.capstone.craftmanhandyman.view.screen.order.RincianPesananProsesScreen
import com.capstone.craftmanhandyman.view.screen.order.RincianPesananSelesaiScreen
import com.capstone.craftmanhandyman.view.screen.profile.HistoryInProfileScreen
import com.capstone.craftmanhandyman.view.screen.profile.ProfileScreen

@Composable
fun CraftmanApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.HistoryInProfile.route &&
                currentRoute != Screen.PesananProses.route &&
                currentRoute != Screen.PesananSelesai.route &&
                currentRoute != Screen.ChatMessage.route )
             {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.History.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Profile.route) {
                ProfileScreen(navHostController = navController)
            }
            composable(Screen.Chat.route) {
                ChatScreen(navController = navController)
            }
            composable(Screen.History.route) {
                HistoryScreen(navController = navController)
            }
            composable(Screen.HistoryInProfile.route) {
                HistoryInProfileScreen(navHostController = navController)
            }

            composable(Screen.PesananProses.route) {
                RincianPesananProsesScreen(navController = navController)
            }
            composable(Screen.PesananSelesai.route) {
                RincianPesananSelesaiScreen(navController = navController, rating = 4)
            }
            composable(Screen.ChatMessage.route) {
                ChatMessageScreen(navController = navController,   navigateBack ={
                    navController.navigateUp()
                })
            }
        }

    }

}