package com.capstone.craftman.view.screen.history

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.R
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.ui.component.HistoryItem
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    HistoryContent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column {
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

                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Pesanan",
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

        Top(navController = navController)
    }
}

@Composable
private fun Top(
    navController: NavHostController,
    context: Context = LocalContext.current,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
) {
    val historyList by viewModel.historyList.observeAsState(listOf())

    LaunchedEffect(true) {
        viewModel.fetchHistory()
    }

    Box(
        modifier = Modifier
    ) {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(historyList.size) { index ->
                val history = historyList[index]
                val screenDestination = when (history.status) {
                    "selesai" -> Screen.PesananSelesai // Tentukan screen tujuan jika selesai
                    "proses" -> Screen.PesananProses // Tentukan screen tujuan jika proses
                    else -> null
                }

                HistoryItem(
                    name = history.name,
                    photoUrl = history.photoUrl,
                    job = history.job,
                    status = history.status,
                    onClick = {
                        screenDestination?.let { destination ->
                            navController.navigate(destination.route)
                        }
                    }
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHistory(){
    HistoryScreen(navController =  rememberNavController())
}