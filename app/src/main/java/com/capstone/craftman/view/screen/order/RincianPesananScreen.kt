package com.capstone.craftman.view.screen.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.R
import com.capstone.craftman.ui.component.ButtonBayar
import com.capstone.craftman.ui.navigation.Screen

@Composable
fun RincianPesananSelesaiScreen(
    rating: Int,
    navController: NavHostController,
){
    Column( modifier = Modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBarr(navController)

        Image(
            painter = painterResource(R.drawable.ricky_harun),
            contentDescription = "Foto profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 36.dp)
                .clip(shape = CircleShape)
        )
        Text(text = "Ricky Harun", modifier = Modifier.padding(top = 16.dp), style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold)), fontSize = 16.sp ))
        Text(text = "Tukang Ac", modifier = Modifier.padding(top = 8.dp), style = TextStyle(fontFamily = FontFamily(Font(R.font.regular)), fontSize = 16.sp ))
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.kirim_pesan)),
            onClick = {
                // Aksi ke chat
            },
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.semibold))
            ),
            modifier = Modifier.padding(top = 16.dp)

            )

        Text(text = "Rating Craftman", modifier = Modifier.padding(top = 16.dp), style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold)), fontSize = 16.sp ))
        RatingBar(rating = rating)
    }
}

@Composable
fun RincianPesananProsesScreen(
    navController: NavHostController,
){
    Column( modifier = Modifier
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopBarr(navHostController = navController)

        Image(
            painter = painterResource(R.drawable.ricky_harun),
            contentDescription = "Foto profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 36.dp)
                .clip(shape = CircleShape)
        )
        Text(text = "Andi", modifier = Modifier.padding(top = 16.dp), style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold)), fontSize = 16.sp ))
        Text(text = "Tukang Ac", modifier = Modifier.padding(top = 8.dp), style = TextStyle(fontFamily = FontFamily(Font(R.font.regular)), fontSize = 16.sp ))
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.kirim_pesan)),
            onClick = {
                navController.navigate(Screen.ChatMessage.route)
            },
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.semibold))
            ),
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .padding(bottom = 36.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            ButtonBayar(price = 205000, onClick = { navController.navigate(Screen.Feedback.route)})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarr(
    navHostController : NavHostController
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.brown_banner)
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                onClick = { navHostController.navigate(Screen.Home.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Rincian Pesanan",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.semibold))
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                shape = RoundedCornerShape(
                    bottomStart = 45.dp,
                    bottomEnd = 45.dp
                )
                clip = true
            }
    )
}


@Composable
private fun RatingBar(rating: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        repeat(rating) {
            Icon(
                painter = painterResource(R.drawable.ic_filled_star),
                contentDescription = "Filled Star",
                tint = colorResource(id = R.color.gold),
                modifier = Modifier.size(24.dp)
            )
        }
        repeat(5 - rating) {
            Icon(
                painter = painterResource(R.drawable.ic_outline_star),
                contentDescription = "Outline Star",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
private fun PreviewRincianPesanan(){
    RincianPesananSelesaiScreen(rating = 4,rememberNavController())
}
