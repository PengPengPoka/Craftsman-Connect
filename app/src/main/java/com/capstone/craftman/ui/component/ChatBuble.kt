package com.capstone.craftman.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.craftman.R
import com.capstone.craftman.data.fake.Message


@Composable
fun ChatBubble(message: Message, isUserMessage: Boolean) {
    val bubbleColor = if (isUserMessage) {
        colorResource(id = R.color.grey)
    } else {
        colorResource(id = R.color.cream)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start,
    ) {
        Column(
            modifier = Modifier
                .background(color = bubbleColor, shape = RoundedCornerShape(16.dp))
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message.text,
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp),
            )
        }
    }
}