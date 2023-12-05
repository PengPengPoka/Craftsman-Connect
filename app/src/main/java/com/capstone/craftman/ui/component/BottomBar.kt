package com.capstone.craftman.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.capstone.craftman.R
import com.capstone.craftman.ui.navigation.NavigationItem
import com.capstone.craftman.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = colorResource(id = R.color.brown_banner)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = ImageBitmap.imageResource(id = R.drawable.ic_home),
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_chat),
                icon = ImageBitmap.imageResource(id = R.drawable.ic_chat),
                screen = Screen.Chat
            ),
            NavigationItem(
                title = stringResource(R.string.menu_histoy_order),
                icon = ImageBitmap.imageResource(id = R.drawable.ic_history_order),
                screen = Screen.History
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                modifier = Modifier,
                icon = {
                    Icon(
                        bitmap = item.icon,
                        contentDescription = item.title,
                        tint = colorResource(id = R.color.brown_bar),
                        modifier = Modifier.size(18.dp)
                    )
                },
                label = {
                    Text(
                        item.title, style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.semibold)),
                            fontSize = 12.sp,
                            color = Color.White,
                        )
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }

}