package com.capstone.craftman.view.screen.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.capstone.craftman.R
import com.capstone.craftman.data.fake.dummyService
import com.capstone.craftman.ui.component.Search
import com.capstone.craftman.ui.component.ServiceRow
import com.capstone.craftman.ui.navigation.Screen

@Composable
fun HomeScreen(
    navHostController: NavHostController
){
    val scrollStateVertical = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollStateVertical)
            .navigationBarsPadding().imePadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },

        ) {
        HomeContent(navHostController = navHostController)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit){
        // Menghentikan input saat pengguna mengetuk di luar komponen pencarian
        focusManager.clearFocus()
    }

    TopAppBar(
        title = {
        },
        actions = {
            IconButton(onClick = {
                navHostController.navigate(Screen.Profile.route)
            }) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Logout",

                    )
            }
        }
    )
    Column {
        Search( Modifier.padding(top = 40.dp),  navHostController = navHostController)

        Text(
            modifier = Modifier
                .padding(start = 36.dp, end = 16.dp, bottom = 16.dp),
            text = "Semua layanan",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.semibold)),
                color = Color(0xFF000000),
            )
        )

        
        ServiceRow(listService = dummyService)

        Text(
            modifier = Modifier
                .padding(start = 36.dp, end = 16.dp, bottom = 16.dp, top = 24.dp),
            text = "Layanan terpopuler",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.semibold)),
                color = Color(0xFF000000),
            )
        )
        ServiceRow(listService = dummyService.sortedByDescending { it.title })
    }
}