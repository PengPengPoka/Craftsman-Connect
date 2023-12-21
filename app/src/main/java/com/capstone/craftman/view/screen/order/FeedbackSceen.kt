package com.capstone.craftman.view.screen.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import com.capstone.craftman.ui.navigation.Screen

@Composable
fun FeedbackScreen(
    navController: NavHostController,
) {
    var rating by remember { mutableIntStateOf(0) }
    var feedback by remember { mutableStateOf("") }
    val scrollStateVertical = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollStateVertical)
        .navigationBarsPadding()
        .imePadding()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }, horizontalAlignment = Alignment.CenterHorizontally) {
        TopBarr(navHostController = navController)

        Image(
            painter = painterResource(R.drawable.ricky_harun),
            contentDescription = "Foto profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 36.dp)
                .clip(shape = CircleShape)
        )

        Text(
            text = "Berikan Masukan",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        RatingBar(value = rating, onValueChange = { rating = it })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { },colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.grey),)) {
                Text("Layanan Bagus", color = Color.Black)
            }
            Button(onClick = { },colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.grey),)) {
                Text("Ramah", color = Color.Black)
            }
            Button(onClick = { },colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.grey),)) {
                Text("Sikap Baik", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = feedback,
            onValueChange = { feedback = it },
            label = { Text("Masukkan masukan Anda di sini") },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
                .weight(1f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.grey),
            )
        )

        Box(
            modifier = Modifier
                .padding(bottom = 36.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            Button(onClick = { navController.navigate(Screen.Home.route){
                popUpTo(0)
            } },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.brown),),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(shape = RoundedCornerShape(10.dp))) {
                Text("Kirim")
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarr(
    navHostController: NavHostController
) {
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
                    text = "Beri Feedback",
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
fun RatingBar(value: Int, onValueChange: (Int) -> Unit) {
    Row {
        for (i in 1..5) {
            Icon(
                painter = if (value >= i) painterResource(R.drawable.ic_filled_star) else painterResource(R.drawable.ic_outline_star),
                tint = colorResource(id = R.color.gold),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onValueChange(i) }
                    .size(36.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedback() {
    FeedbackScreen(navController = rememberNavController())
}
