package com.capstone.craftmanhandyman.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.craftmanhandyman.ui.navigation.Screen
import com.capstone.craftmanhandyman.view.screen.login.LoginScreen
import com.capstone.craftmanhandyman.view.screen.register.RegisterScreen

@Composable
fun LoginRegisterApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {
        composable(Screen.Login.route) {
            // Your main composable function
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route) {
            // Composable function for registration screen
            RegisterScreen(navController = navController)

        }
    }
}