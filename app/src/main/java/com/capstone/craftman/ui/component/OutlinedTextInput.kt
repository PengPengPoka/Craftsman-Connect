package com.capstone.craftman.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextInput(
    input : String,
    placeholder : String,
    keyboardType: KeyboardType,
    onValueChange : (String) -> Unit,
){
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = input,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
//            .padding(start = 36.dp, end = 36.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            },
    )

}