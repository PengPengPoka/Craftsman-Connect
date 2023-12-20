package com.capstone.craftman.view.screen.list_craftman

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.craftman.R
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.helper.UiState
import com.capstone.craftman.response.CraftmanResponse
import com.capstone.craftman.ui.component.ListCraftmanItem
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCraftmanScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigateToDetail: (String, String, String) -> Unit,
){
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
                    IconButton(
                        onClick = { navHostController.navigate(Screen.Home.route){
                            popUpTo(0)
                        } }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Temukan tukang sesuai \ndengan kebutuhan ganda",
                        style = TextStyle(
                            fontSize = 14.sp,
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

        Top(navHostController = navHostController, navigateToDetail = navigateToDetail)
    }


}


@Composable
private fun Top(navHostController: NavHostController,
                context: Context = LocalContext.current,
                viewModel: ListCraftmanViewModel = viewModel(
                    factory = ViewModelFactory(Injection.provideRepository(context))
                ),
                navigateToDetail: (String, String, String) -> Unit,
){
    val tukangList by viewModel.TukangList.observeAsState()

    LaunchedEffect(true) {
        viewModel.getTukang()
    }

    Box(
        modifier = Modifier
    ) {
        LazyColumn(state = rememberLazyListState(),
            modifier = Modifier.padding(top = 8.dp)) {

            when(tukangList){
                is UiState.Loading -> {
                    // Display loading indicator if needed
                }

                is UiState.Success -> {
                    val tukang =
                        (tukangList as UiState.Success<CraftmanResponse>).data.craftmanList
                    items(tukang) { craftman ->
                        ListCraftmanItem(modifier = Modifier, craftman = craftman, navigateToDetail = navigateToDetail)
                    }
                }
                is UiState.Error -> {
                    // Handle error state if needed
                }

                else -> {}

                }

        }

    }
}

