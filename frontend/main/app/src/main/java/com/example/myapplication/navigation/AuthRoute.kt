package com.example.myapplication.navigation

sealed class AuthRoute(val route: String) {
    data object Splash : AuthRoute("splash")
    data object Welcome : AuthRoute("welcome")
    data object Register : AuthRoute("register")
    data object Login : AuthRoute("login")
}
