package com.capstone.craftman.view

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.capstone.craftman.ui.component.BottomBar
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.screen.chat.ChatMessageScreen
import com.capstone.craftman.view.screen.chat.ChatScreen
import com.capstone.craftman.view.screen.detailcraftman.DetailScreen
import com.capstone.craftman.view.screen.history.HistoryScreen
import com.capstone.craftman.view.screen.home.HomeScreen
import com.capstone.craftman.view.screen.list_craftman.ListCraftmanScreen
import com.capstone.craftman.view.screen.order.FeedbackScreen
import com.capstone.craftman.view.screen.order.RincianPesananProsesScreen
import com.capstone.craftman.view.screen.order.RincianPesananSelesaiScreen
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
                currentRoute != "${Screen.DetailCraftman.route}/{name}/{layanan}/{deskripsi}" &&
                currentRoute != Screen.Feedback.route &&
                currentRoute != Screen.PesananProses.route &&
                currentRoute != Screen.PesananSelesai.route &&
                currentRoute != Screen.ChatMessage.route &&
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
                ChatScreen(navController = navController)
            }
            composable(Screen.History.route) {
                HistoryScreen(navController = navController)
            }
            composable(Screen.HistoryInProfile.route) {
                HistoryInProfileScreen(navHostController = navController)
            }
            composable(Screen.ListCraftman.route) {
                ListCraftmanScreen(navHostController = navController) { name, layanan, deskripsi ->
                    navController.navigate(
                        "${Screen.DetailCraftman.route}/$name/$layanan/$deskripsi"
                    )
                }
            }
            composable(
                route = "${Screen.DetailCraftman.route}/{name}/{layanan}/{deskripsi}",
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("layanan") { type = NavType.StringType },
                    navArgument("deskripsi") { type = NavType.StringType }
                )
            ) {
                val name = it.arguments?.getString("name") ?: " "
                val layanan = it.arguments?.getString("layanan") ?: " "
                val deskripsi = it.arguments?.getString("deskripsi") ?: " "
                DetailScreen(navHostController = navController, name = name, layanan = layanan, deskripsi = deskripsi)
            }

            composable(Screen.PesananProses.route) {
                RincianPesananProsesScreen(navController = navController)
            }
            composable(Screen.PesananSelesai.route) {
                RincianPesananSelesaiScreen(navController = navController, rating = 4)
            }
            composable(Screen.Feedback.route) {
                SetWindowSoftInputMode(navController = navController) {
                    FeedbackScreen(navController = navController)
                }
            }
            composable(Screen.ChatMessage.route) {
                ChatMessageScreen(navController = navController,   navigateBack ={
                    navController.navigateUp()
                })
            }
        }

    }

}

@Composable
fun SetWindowSoftInputMode(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    var previousWindowSoftInputMode by rememberSaveable { mutableStateOf(0) }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        previousWindowSoftInputMode = (context as ComponentActivity).window.attributes.softInputMode
        context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        onDispose {
            context.window.setSoftInputMode(previousWindowSoftInputMode)
        }
    }

    content()
}