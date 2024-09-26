package com.example.a42userinfo

sealed interface MainDestination {
    val route: String
}

data object Login : MainDestination {
    override val route = "login"
}

data object Home : MainDestination {
    override val route = "home"
}