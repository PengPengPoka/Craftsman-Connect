package com.capstone.craftman.view.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
 HomeContent(navHostController = navHostController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
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
        Search( Modifier.padding(top = 40.dp))

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

        ServiceRow(listService = dummyService)

    }

}