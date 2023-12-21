package com.capstone.craftmanhandyman.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.craftmanhandyman.R

@Composable
fun ButtonBayar(
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
            Text(
                text = "Pesanan telah diselesaikan",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.semibold)),
                    color = Color.White
                )
            )

    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewButtonBayar() {
    ButtonBayar( onClick = {})
}