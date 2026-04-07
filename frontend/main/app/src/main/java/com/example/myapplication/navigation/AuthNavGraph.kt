package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.RegisterScreen
import com.example.myapplication.ui.screens.SplashScreen
import com.example.myapplication.ui.screens.WelcomeScreen

@Composable
fun AuthNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AuthRoute.Splash.route
    ) {
        composable(AuthRoute.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(AuthRoute.Welcome.route) {
                        popUpTo(AuthRoute.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthRoute.Welcome.route) {
            WelcomeScreen(
                onRegisterClick = {
                    navController.navigate(AuthRoute.Register.route)
                },
                onLoginClick = {
                    navController.navigate(AuthRoute.Login.route)
                }
            )
        }

        composable(AuthRoute.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    navController.navigate(AuthRoute.Login.route) {
                        popUpTo(AuthRoute.Register.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(AuthRoute.Login.route)
                }
            )
        }

        composable(AuthRoute.Login.route) {
            LoginScreen(
                onLoginClick = {
                    // TODO: обработка успешного входа
                },
                onRegisterClick = {
                    navController.navigate(AuthRoute.Register.route)
                },
                onForgotPasswordClick = {
                    // TODO: экран восстановления пароля
                }
            )
        }
    }
}