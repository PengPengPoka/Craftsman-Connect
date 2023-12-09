package com.capstone.craftman.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.capstone.craftman.R

@Composable
fun ButtonOrder(
    modifier: Modifier = Modifier,
    price : Int,
    onClick : () -> Unit,
){
    Button(
        onClick = { /* Lakukan sesuatu ketika icon ditekan */ },
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.brown_bar),
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pesan dan panggil sekarang",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.semibold)),
                    color = Color.White
                )
            )

            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.price , price),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.semibold)),
                    color = Color.White
                )
            )
        }
    }
}