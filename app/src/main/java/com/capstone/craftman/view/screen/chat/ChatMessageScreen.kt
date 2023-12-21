package com.capstone.craftman.view.screen.chat

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.R
import com.capstone.craftman.data.fake.Message
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.ui.component.ChatBubble
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory


@Composable
fun ChatMessageScreen(
    navController: NavHostController,
    context: Context = LocalContext.current,
    viewModel: ChatViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateBack: () -> Unit,
) {

    var text by remember { mutableStateOf("") }
    val chatMessages by viewModel.chatMessage.observeAsState(emptyList())

    val groupedMessages = groupMessages(chatMessages)
    val interactionSource = remember { MutableInteractionSource() }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val wasFocused = remember { isFocused }

    var isUserMessage by remember { mutableStateOf(false) }

    LaunchedEffect(chatMessages) {
        viewModel.fetchChatMessage()
        if (wasFocused) {
            focusRequester.requestFocus()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBarr(navHostController = navController, navigateBack)
        val scrollState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = scrollState,
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(groupedMessages) { messages ->
                messages.forEach { message ->
                    ChatBubble(message, isUserMessage)
                    isUserMessage = !message.isFromMe
                }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    val message = Message(text, true)
                    viewModel.addMessage(message)
                    text = ""
                }),
                interactionSource = interactionSource,
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.brown_banner),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.brown_banner),
                    disabledIndicatorColor = colorResource(id = R.color.brown_banner),
                    cursorColor = Color.White
                )
            )

            IconButton(
                colors = IconButtonDefaults.iconButtonColors(colorResource(id = R.color.brown_banner)
                ),
                onClick = {
                    val message = Message(text, true)
                    viewModel.addMessage(message)
                    text = ""
                },
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Send",
                    modifier = Modifier,
                    tint = Color.White
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarr(
    navHostController: NavHostController,
    navigateBack: () -> Unit,
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
                    onClick = navigateBack
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = { navHostController.navigate(Screen.Home.route) }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ricky_harun),
                        contentDescription = "Back",
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Akbar",
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

fun groupMessages(chatMessages: List<Message>): List<List<Message>> {
    val grouped = mutableListOf<MutableList<Message>>()

    if (chatMessages.isNotEmpty()) {
        var currentGroup = mutableListOf(chatMessages[0])
        for (i in 1 until chatMessages.size) {
            val previousMessage = chatMessages[i - 1]
            val currentMessage = chatMessages[i]

            if (previousMessage.isFromMe != currentMessage.isFromMe) {
                grouped.add(currentGroup)
                currentGroup = mutableListOf()
            }
            currentGroup.add(currentMessage)
        }
        grouped.add(currentGroup)
    }

    return grouped
}

@Preview(showBackground = true)
@Composable
private fun PreviewChat() {
    ChatMessageScreen(rememberNavController(), navigateBack = {})
}

