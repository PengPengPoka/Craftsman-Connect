package com.capstone.craftman.view.screen.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.craftman.R
import com.capstone.craftman.data.injection.Injection
import com.capstone.craftman.ui.component.OutlinedTextInput
import com.capstone.craftman.ui.navigation.Screen
import com.capstone.craftman.view.ViewModelFactory
import com.capstone.craftman.view.screen.login.LoginViewModel

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val scrollStateVertical = rememberScrollState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollStateVertical)
            .navigationBarsPadding().imePadding(),
    ) {
        RegisterContent(navController = navController)
    }
}

@Composable
fun RegisterContent(
    context: Context = LocalContext.current,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navController: NavHostController,
) {

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var noTelp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    var showLoading by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(value = false) }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    val wasFocused = remember { isFocused }

    LaunchedEffect(true) {
        if (wasFocused) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start,
    ) {

        Text(
            modifier = Modifier
                .padding(start = 36.dp, end = 16.dp, bottom = 16.dp),
            text = "Selamat datang di Craftman Connect",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.semibold)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        Text(
            modifier = Modifier
                .padding(start = 36.dp, end = 16.dp, bottom = 16.dp),
            text = "Daftarkan diri Anda sekarang dan temukan \nteknisi yang tepat untuk pekerjaan Anda. ",
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                textAlign = TextAlign.Justify,
            )
        )

        /*Input Nama*/
        Text(
            modifier = Modifier
                .padding(start = 48.dp, end = 36.dp),
            text = "Nama",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF8A7B7B),
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextInput(
                input = nama,
                placeholder = "Masukkan nama",
                keyboardType = KeyboardType.Text,
                onValueChange = { nama = it })
        }

        /*Input Email*/

        Text(
            modifier = Modifier
                .padding(start = 48.dp, end = 36.dp),
            text = "Email",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF8A7B7B),
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextInput(
                input = email,
                placeholder = "Masukkan email",
                keyboardType = KeyboardType.Email,
                onValueChange = { email = it })
        }

        /*input No handphone*/

        Text(
            modifier = Modifier
                .padding(start = 48.dp, end = 36.dp),
            text = "No Handphone",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF8A7B7B),
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextInput(
                input = noTelp,
                placeholder = "Masukkan no handphone",
                keyboardType = KeyboardType.Number,
                onValueChange = { noTelp = it })
        }

        /*Input Password*/

        Text(
            modifier = Modifier
                .padding(start = 48.dp, end = 36.dp),
            text = "Password",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF8A7B7B),
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = password,
                placeholder = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                onValueChange = { newInput ->
                    password = newInput
                },
                shape = RoundedCornerShape(size = 12.dp),
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { //restore keyboard while rotation
                        isFocused = it.isFocused
                    },
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ElevatedButton(
                modifier = Modifier
                    .width(260.dp),
                onClick = {
                    if (password.length < 8) {
                        Toast.makeText(context, "Password kurang dari 8", Toast.LENGTH_SHORT).show()
                        return@ElevatedButton
                    }

                    navController.navigate(Screen.Login.route){
                        popUpTo(0)
                    }

                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.brown)
                ),
            ) {
                if (showLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.Gray
                    )
                } else {
                    Text(
                        text = "Register", style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.semibold)),
                            fontWeight = FontWeight(400),
                            color = Color.White,
                        )
                    )
                }
            }

            Text(text = "OR", style = TextStyle(fontFamily = FontFamily(Font(R.font.semibold))))

            Row {
                Text(text = stringResource(id = R.string.to_login))

                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.login)),
                    onClick = {
                              navController.navigate(Screen.Login.route){
                                  popUpTo(0)
                              }
                    },
                    style = TextStyle(
                        color = colorResource(id = R.color.gold),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.semibold))
                    ),

                    )
            }
        }

    }
}
