package com.capstone.craftman.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.craftman.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryItem(
    name: String,
    @DrawableRes photoUrl: Int,
    job: String,
    status: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Aksi saat diklik */ }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(id = photoUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.weight(1f).padding(start = 16.dp)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = job,
                    fontWeight = FontWeight.Thin,
                )
            }
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = { /* Lakukan sesuatu ketika icon ditekan */ },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentSize(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (status == "selesai") Color.Green else Color.Yellow,
                    )
                ) {
                    Text(
                        text = status,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold)), color = Color.Black)
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewButton(){
    HistoryItem(name = "Andi", photoUrl = R.drawable.ricky_harun, job = "Tukang ac", status = "selesai", onClick = {})
}