package com.example.a42userinfo.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a42userinfo.R
import com.example.a42userinfo.ui.components.CustomButton

@Composable
fun LoginScreen(
    onClickLogin: () -> Unit = {},
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    loginViewModel.logOut()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo42),
            contentDescription = "Logo 42",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Crop
        )

        CustomButton(text = stringResource(R.string.login_button), onClick = onClickLogin)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}