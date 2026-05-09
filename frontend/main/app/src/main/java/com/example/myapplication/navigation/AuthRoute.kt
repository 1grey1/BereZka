package com.example.myapplication.navigation

/**
 * Набор маршрутов (routes) для навигации в auth-графе.
 *
 * Используется вместе с Navigation Compose:
 * - каждый объект представляет отдельный экран;
 * - route — это строковый идентификатор экрана;
 * - применяется в NavHost и при навигации через NavController.
 *
 * sealed class позволяет:
 * - гарантировать фиксированный набор экранов;
 * - безопасно работать с ними (when, автокомплит).
 */
sealed class AuthRoute(val route: String) {

    /**
     * Splash Screen (экран загрузки).
     *
     * Первый экран при запуске приложения.
     */
    data object Splash : AuthRoute("splash")

    /**
     * Welcome Screen (приветственный экран).
     *
     * Показывается после splash, содержит:
     * - кнопку регистрации
     * - кнопку входа
     */
    data object Welcome : AuthRoute("welcome")

    /**
     * Register Screen (экран регистрации).
     *
     * Форма создания нового аккаунта.
     */
    data object Register : AuthRoute("register")

    /**
     * Login Screen (экран авторизации).
     *
     * Форма входа в существующий аккаунт.
     */
    data object Login : AuthRoute("login")
    data object DebugDb : AuthRoute("debug_db")
    data object ChatList : AuthRoute("chat_list")
    data object ChatDialog : AuthRoute("chat_dialog/{chatId}/{peerName}")
}
