package com.example.myapplication.data.repository

import com.example.myapplication.data.model.AuthRequest
import com.example.myapplication.data.remote.RetrofitClient

/**
 * Репозиторий для работы с авторизацией.
 *
 * Это слой между ViewModel и API:
 * - принимает "чистые" данные от UI;
 * - вызывает методы ApiService;
 * - обрабатывает ответы и ошибки;
 * - возвращает результат в удобном виде (Result).
 */
class AuthRepository {

    // Получаем экземпляр API из RetrofitClient
    private val api = RetrofitClient.api

    /**
     * Регистрация пользователя.
     *
     * @param nickname имя пользователя
     * @param password пароль
     *
     * @return Result<String>:
     * - success -> сообщение от сервера
     * - failure -> ошибка (Exception)
     */
    suspend fun register(nickname: String, password: String): Result<String> {
        return try {
            // Выполняем запрос к API
            val response = api.register(AuthRequest(nickname, password))

            if (response.isSuccessful) {
                // Успешный HTTP-ответ (200-299)
                Result.success(
                    // Берём сообщение из body или fallback "OK"
                    response.body()?.message ?: "OK"
                )
            } else {
                // Ошибка от сервера (например 400/401/500)
                Result.failure(
                    Exception(response.errorBody()?.string())
                )
            }

        } catch (e: Exception) {
            // Ошибка на уровне сети / парсинга / таймаута
            Result.failure(e)
        }
    }

    /**
     * Авторизация пользователя (логин).
     *
     * @param nickname имя пользователя
     * @param password пароль
     *
     * @return Result<String>:
     * - success -> сообщение от сервера
     * - failure -> ошибка
     */
    suspend fun login(nickname: String, password: String): Result<String> {
        return try {
            // Выполняем запрос к API
            val response = api.login(AuthRequest(nickname, password))

            if (response.isSuccessful) {
                // Успешный ответ
                Result.success(
                    response.body()?.message ?: "OK"
                )
            } else {
                // Ошибка от сервера
                Result.failure(
                    Exception(response.errorBody()?.string())
                )
            }

        } catch (e: Exception) {
            // Ошибка сети или неожиданное исключение
            Result.failure(e)
        }
    }
}