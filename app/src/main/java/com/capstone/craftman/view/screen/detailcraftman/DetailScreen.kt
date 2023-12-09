package com.capstone.craftman.view.screen.detailcraftman

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.R
import com.capstone.craftman.data.fake.Craftmans
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.ui.component.ButtonOrder
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    context: Context = LocalContext.current,
    name: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {
    val craftmans by viewModel.craftsman.observeAsState()

    LaunchedEffect(true) {
        viewModel.getCraftsmanByName(name)
    }

    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.brown_banner)
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            navHostController.navigate(Screen.ListCraftman.route) {
                                popUpTo(Screen.ListCraftman.route) {
                                    inclusive = true // untuk kembali kehalaman sebelum nya
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Craftman Profile",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.semibold))
                        ),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Biarkan kosong agar text ditengah */ }) {
                    }
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
        craftmans?.let { craftsmanData ->
            containerOne(navHostController, craftsmanData)
        }
    }
}

@Composable
private fun containerOne(
    navController: NavHostController,
    craftman: Craftmans
) {

    Column {
        Row(

            modifier = Modifier.padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = craftman.photoUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .wrapContentSize()
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = craftman.name,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold))),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = craftman.job,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.regular))),
                    fontWeight = FontWeight.Thin,
                )
            }

        }

        Text(
            text = "Kemampuan dan pengalaman",
            style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold)), fontSize = 18.sp),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 18.dp)
        )

        Text(
            text = stringResource(id = R.string.lorem),
            style = TextStyle(fontFamily = FontFamily(Font(R.font.regular)), fontSize = 16.sp),
            fontWeight = FontWeight.Thin,
            modifier = Modifier.padding(start = 18.dp, top = 8.dp)
        )



        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            ButtonOrder(price = craftman.price, onClick = {

            })
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun PreviewDetail() {
    DetailScreen(navHostController = rememberNavController(), name = "Andi")
}