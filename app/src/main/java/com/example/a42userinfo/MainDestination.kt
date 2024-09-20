package com.example.a42userinfo

sealed interface RallyDestination {
    val route: String
}

data object Login : RallyDestination {
    override val route = "login"
}

data object Home : RallyDestination {
    override val route = "home"
}

val rallyTabRowScreens = listOf(Login, Home)