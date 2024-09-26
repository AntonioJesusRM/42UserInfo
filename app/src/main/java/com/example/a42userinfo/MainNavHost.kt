package com.example.a42userinfo

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a42userinfo.ui.home.HomeScreen
import com.example.a42userinfo.ui.login.LoginScreen

@Composable
fun MainNavHost(
    navController: NavHostController, code: String?
) {
    val context = LocalContext.current
    BackHandler {
        (context as? Activity)?.finishAffinity()
    }
    NavHost(
        navController = navController,
        startDestination = if (code != null) Home.route else Login.route
    ) {
        composable(route = Login.route) {
            LoginScreen(onClickLogin = {
                openLoginInGoogle(context)
            })
        }
        composable(route = Home.route) {
            HomeScreen(
                onLogOutClick = {
                    navController.navigateSingleTopTo(Login.route)
                }, code = code
            )
        }
    }
}

fun openLoginInGoogle(context: Context) {
    val loginUrl =
        "https://api.intra.42.fr/oauth/authorize?client_id=u-s4t2ud-ea04372b73f44034442591b159c1c06e3ebbac43606abd1169439307a273492d&redirect_uri=com.example.a42userinfo%3A%2F%2Foauth%2Fcallback&response_type=code"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loginUrl))
    try {
        startActivity(context, intent, null)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
    }
}


fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}
