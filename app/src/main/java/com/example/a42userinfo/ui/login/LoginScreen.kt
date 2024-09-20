package com.example.a42userinfo.ui.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    onClickLogin: () -> Unit = {},
) {
    Button(onClick = onClickLogin) {
        Text(text = "Ir a home")
    }
}