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

/**
 * Навигационный граф для экранов авторизации.
 *
 * Отвечает за:
 * - определение всех экранов (routes);
 * - переходы между ними;
 * - начальную точку входа (startDestination).
 *
 * Использует Navigation Compose.
 */
@Composable
fun AuthNavGraph(
    // NavController управляет навигацией между экранами.
    // Если не передан извне — создаётся здесь.
    navController: NavHostController = rememberNavController()
) {
    // NavHost — контейнер, в котором происходит переключение экранов.
    NavHost(
        navController = navController,

        // Стартовый экран при запуске графа.
        startDestination = AuthRoute.Splash.route
    ) {

        /**
         * Splash Screen
         *
         * Первый экран приложения.
         * После задержки автоматически переходит на WelcomeScreen.
         */
        composable(AuthRoute.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(AuthRoute.Welcome.route) {

                        // Удаляем Splash из back stack,
                        // чтобы пользователь не мог вернуться назад на него.
                        popUpTo(AuthRoute.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        /**
         * Welcome Screen
         *
         * Экран приветствия с двумя действиями:
         * - регистрация
         * - вход
         */
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

        /**
         * Register Screen
         *
         * Экран регистрации пользователя.
         */
        composable(AuthRoute.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    // После успешной регистрации переходим на логин
                    // и убираем экран регистрации из стека.
                    navController.navigate(AuthRoute.Login.route) {
                        popUpTo(AuthRoute.Register.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    // Переход на экран логина (если пользователь передумал регистрироваться)
                    navController.navigate(AuthRoute.Login.route)
                }
            )
        }

        /**
         * Login Screen
         *
         * Экран авторизации пользователя.
         */
        composable(AuthRoute.Login.route) {
            LoginScreen(
                onLoginClick = {
                    // TODO: обработка успешного входа
                    // Здесь обычно происходит переход в основной граф приложения (MainNavGraph)
                },
                onRegisterClick = {
                    // Переход на регистрацию
                    navController.navigate(AuthRoute.Register.route)
                },
                onForgotPasswordClick = {
                    // TODO: экран восстановления пароля
                }
            )
        }
    }
}