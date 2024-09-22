package com.example.a42userinfo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.a42userinfo.ui.theme.UserInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserInfoTheme("hades") {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    UserInfoApp(intent)
                }
            }
        }
    }
}

@Composable
fun UserInfoApp(intent: Intent) {
    var authCode by remember { mutableStateOf<String?>(null) }
    val navController = rememberNavController()
    intent.data?.let { uri ->
        if (uri.toString().startsWith("com.example.a42userinfo://oauth/callback")) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                authCode = code
            }
        }
    }
    MainNavHost(
        navController = navController,
        code = authCode
    )
}