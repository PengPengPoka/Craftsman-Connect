package com.capstone.craftman.view.screen.chat

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.capstone.craftman.ui.component.ChatItem
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.brown_banner)
        ),
        title = {},
        navigationIcon = {},
    )
    ChatContent(navController = navController)
}

@Composable
fun ChatContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context = LocalContext.current,
    viewModel: ChatViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    )
){
    val chatList by viewModel.chatList.observeAsState(listOf())

    LaunchedEffect(true) {
        viewModel.fetchChat()
    }

    Box(
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(top = 80.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                modifier = Modifier,
                color = Color.White,
                text = "Chat", style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(
                        Font(R.font.semibold)
                    )
                )
            )

            LazyColumn(state = rememberLazyListState(),
                modifier = Modifier.padding(top = 8.dp)) {
                items(chatList.size) { index ->
                    val chat = chatList[index]
                    ChatItem(name = chat.name, photoUrl = chat.image, onClick = {navController.navigate(Screen.ChatMessage.route)})
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ChatPreview(){
    ChatScreen(navController = rememberNavController())
}