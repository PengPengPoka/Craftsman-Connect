package com.capstone.craftman.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.craftman.R

@Composable
fun ButtonBayar(
    price: Int,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.brown_bar),
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total biaya : ",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.semibold)),
                    color = Color.White
                )
            )

            Text(
                modifier = Modifier.padding(start = 25.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.price, price),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.semibold)),
                    color = Color.White
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewButtonBayar() {
    ButtonBayar(5000, onClick = {})
}