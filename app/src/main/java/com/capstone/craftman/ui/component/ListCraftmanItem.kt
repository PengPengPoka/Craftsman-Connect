package com.capstone.craftman.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.craftman.R

@Composable
fun ListCraftmanItem(
    name: String,
    @DrawableRes photoUrl: Int,
    job: String,
    location: String,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.cream)
        ),
        modifier = Modifier
            .padding(24.dp)
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
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
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
                Row(
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(modifier = Modifier.align(Alignment.CenterVertically),bitmap = ImageBitmap.imageResource(id = R.drawable.ic_location), contentDescription = "location")
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = location,
                        fontWeight = FontWeight.Thin,
                    )
                }
            }
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = { /* Lakukan sesuatu ketika icon ditekan */ },
                    modifier = Modifier.wrapContentSize()
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.brown_bar),
                    )
                ) {
                    Text(
                        text = "Pilih",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold)), color = Color.White)
                    )
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCraftmanitem(){
    ListCraftmanItem(name = "Andi", photoUrl = R.drawable.ricky_harun, job = "Teknisi Ac", location = "Yogyakarta, Sleman")
}