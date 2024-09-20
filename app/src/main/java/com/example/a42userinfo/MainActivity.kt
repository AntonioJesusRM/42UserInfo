package com.example.a42userinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.a42userinfo.ui.theme._42UserInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _42UserInfoTheme {
                UserInfoApp()
            }
        }
    }
}

@Composable
fun UserInfoApp() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        RallyNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
