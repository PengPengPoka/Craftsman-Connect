package com.capstone.craftman.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.capstone.craftman.R
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.ui.theme.CraftmanTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    DisposableEffect(Unit) {
        onDispose {
            keyboardController?.hide()
        }
    }

    // Mengatur focus ke komponen pencarian ketika komponen ini dibuat
    BackHandler(enabled = true) {
        focusManager.clearFocus()
    }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { newValue ->
            searchQuery = newValue
        },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            navHostController.navigate(Screen.ListCraftman.route + "?query=$searchQuery")
        }),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentHeight()
            .background(color = Color.White)
            .border(1.dp, MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp)),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder_search))
        },
        shape = RoundedCornerShape(4.dp)

    )
}




@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    CraftmanTheme {
        Search(navHostController = rememberNavController())
    }
}