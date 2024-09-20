package com.example.a42userinfo.ui.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onLogOutClick: () -> Unit = {},
) {
    Button(onClick = onLogOutClick) {
        Text(text = "Ir a login")
    }
}