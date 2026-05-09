package com.example.myapplication.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.ChatDialogScreen
import com.example.myapplication.ui.screens.ChatListScreen
import com.example.myapplication.ui.screens.DebugDbScreen
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
                },
                onDebugClick = {
                    navController.navigate(AuthRoute.DebugDb.route)
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
                    navController.navigate(AuthRoute.ChatList.route) {
                        popUpTo(AuthRoute.Welcome.route)
                    }
                },
                onRegisterClick = {
                    navController.navigate(AuthRoute.Register.route)
                },
                onForgotPasswordClick = {}
            )
        }

        composable(AuthRoute.DebugDb.route) {
            DebugDbScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AuthRoute.ChatList.route) {
            ChatListScreen(
                onChatClick = { chatId, peerName ->
                    navController.navigate("chat_dialog/$chatId/${Uri.encode(peerName)}")
                },
                onBackToWelcome = {
                    navController.navigate(AuthRoute.Welcome.route) {
                        popUpTo(AuthRoute.ChatList.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = AuthRoute.ChatDialog.route,
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType },
                navArgument("peerName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId").orEmpty()
            val peerName = Uri.decode(backStackEntry.arguments?.getString("peerName").orEmpty())
            ChatDialogScreen(
                chatId = chatId,
                peerName = peerName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
