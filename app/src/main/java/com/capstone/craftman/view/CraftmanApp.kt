package com.capstone.craftman.view

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
import com.capstone.craftman.ui.component.BottomBar
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.screen.chat.ChatScreen
import com.capstone.craftman.view.screen.history.HistoryScreen
import com.capstone.craftman.view.screen.home.HomeScreen
import com.capstone.craftman.view.screen.list_craftman.ListCraftmanScreen
import com.capstone.craftman.view.screen.profile.HistoryInProfileScreen
import com.capstone.craftman.view.screen.profile.ProfileScreen

@Composable
fun CraftmanApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Profile.route &&
                currentRoute != Screen.HistoryInProfile.route &&
                currentRoute != Screen.ListCraftman.route)
             {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navHostController = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navHostController = navController)
            }
            composable(Screen.Chat.route) {
                ChatScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.HistoryInProfile.route) {
                HistoryInProfileScreen(navHostController = navController)
            }
            composable(Screen.ListCraftman.route) {
                ListCraftmanScreen(navHostController = navController)
            }
        }

    }

}