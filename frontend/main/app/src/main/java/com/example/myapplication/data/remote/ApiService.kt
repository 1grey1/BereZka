package com.example.myapplication.data.remote

import com.example.myapplication.data.model.AuthRequest
import com.example.myapplication.data.model.LoginResponse
import com.example.myapplication.data.model.MessageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Интерфейс API для работы с сервером (через Retrofit).
 *
 * Здесь описаны все HTTP-запросы, которые может выполнять приложение:
 * - проверка доступности сервера;
 * - регистрация пользователя;
 * - авторизация пользователя.
 *
 * Retrofit автоматически создаёт реализацию этого интерфейса.
 */
interface ApiService {

    /**
     * Проверка доступности сервера (health-check).
     *
     * Выполняет GET-запрос на endpoint: /health
     *
     * Используется для:
     * - проверки, что сервер запущен;
     * - диагностики соединения.
     *
     * @return Response<String> — строковый ответ сервера (например "OK")
     */
    @GET("health")
    suspend fun health(): Response<String>

    /**
     * Регистрация нового пользователя.
     *
     * Выполняет POST-запрос на endpoint: /register
     *
     * В body отправляется AuthRequest:
     * {
     *   "nickname": "...",
     *   "password": "..."
     * }
     *
     * @param request данные пользователя для регистрации
     *
     * @return Response<MessageResponse> — ответ с сообщением
     * (например: "User created successfully")
     */
    @POST("register")
    suspend fun register(
        @Body request: AuthRequest
    ): Response<MessageResponse>

    /**
     * Авторизация пользователя (логин).
     *
     * Выполняет POST-запрос на endpoint: /login
     *
     * В body отправляется AuthRequest:
     * {
     *   "nickname": "...",
     *   "password": "..."
     * }
     *
     * @param request данные пользователя для входа
     *
     * @return Response<LoginResponse> — ответ с данными пользователя:
     * - message
     * - user_id
     * - nickname
     */
    @POST("login")
    suspend fun login(
        @Body request: AuthRequest
    ): Response<LoginResponse>
}