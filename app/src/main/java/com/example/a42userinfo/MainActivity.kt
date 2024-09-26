package com.example.a42userinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.a42userinfo.data.session.DataUserSession
import com.example.a42userinfo.ui.theme.UserInfoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataUserSession: DataUserSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var authCode: String? = null
        intent.data?.let { uri ->
            if (uri.toString().startsWith("com.example.a42userinfo://oauth/callback")) {
                authCode = uri.getQueryParameter("code")
            }
        }

        setContent {
            UserInfoTheme("") {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    UserInfoApp(authCode, dataUserSession)
                }
            }
        }
    }
}

@Composable
fun UserInfoApp(authCode: String?, dataUserSession: DataUserSession) {
    val navController = rememberNavController()

    val startDestination = if (dataUserSession.haveSession()) Home.route else Login.route

    MainNavHost(navController = navController, code = authCode, startDestination = startDestination)
}