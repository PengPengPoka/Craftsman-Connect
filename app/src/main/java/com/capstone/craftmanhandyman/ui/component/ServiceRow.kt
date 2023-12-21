package com.capstone.craftmanhandyman.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capstone.craftmanhandyman.data.fake.FakeService
import com.capstone.craftmanhandyman.ui.component.ServiceCard

@Composable
fun ServiceRow(
    listService: List<FakeService>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listService, key = { it.title}) { service ->
            ServiceCard(service)
        }
    }
}