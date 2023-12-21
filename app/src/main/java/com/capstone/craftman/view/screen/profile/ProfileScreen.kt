package com.capstone.craftman.view.screen.profile

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.R
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.brown_banner)
        ),
        title = {},
        navigationIcon = {},
    )

    Top(navHostController = navHostController)

}

@Composable
private fun Top(navHostController: NavHostController,
        context: Context = LocalContext.current,
        viewModel: ProfileViewModel = viewModel(
            factory = ViewModelFactory(Injection.provideRepository(context))
        )
        ){
        Box(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Banner Image",
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth())

            Column(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_picture),
                    contentDescription = "Foto profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clip(shape = CircleShape)
                )

                Text(text = "Jenklyn", style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.semibold))))

                Text(text = "08743242345", style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.semibold))))


                Spacer(modifier = Modifier.height(48.dp))

                Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)) {
                    Text(text = "Akun", style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.semibold))))

                    Text(text = "jenklyn@gmail.com", style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.semibold))))
                }

                Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)) {
                    Text(text = "Alamat", style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.semibold))))

                    Text(text = "Jln Serdadu, Macanan,\n Sleman Yogyakarta", fontSize = 12.sp)
                }

                Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Riwayat Pesanan", style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(
                            Font(R.font.semibold))))

                    IconButton(onClick = { navHostController.navigate(Screen.HistoryInProfile.route) }) {
                        Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = "Detail riwayat pesanan",
                            modifier = Modifier.size(14.dp))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(
                    onClick = {viewModel.logout()},
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    Text(text = "Keluar", style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.semibold))))
                }

            }


            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.TopStart)
            ) {
                IconButton(
                    onClick = {
                        navHostController.navigate(Screen.Home.route){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    ProfileScreen()
}