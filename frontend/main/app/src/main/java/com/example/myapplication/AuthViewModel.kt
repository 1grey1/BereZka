package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AuthRepository
import kotlinx.coroutines.launch

/**
 * ViewModel для авторизации и регистрации.
 *
 * Отвечает за:
 * - взаимодействие с AuthRepository;
 * - управление состоянием (loading, error);
 * - вызов UI-колбэков (onSuccess).
 *
 * ViewModel переживает пересоздание экрана (например, поворот),
 * поэтому хранит состояние между конфигурациями.
 */
class AuthViewModel : ViewModel() {

    // Репозиторий, через который выполняются все API-запросы.
    private val repo = AuthRepository()

    /**
     * Флаг загрузки.
     *
     * true  — идёт запрос к серверу
     * false — запрос завершён
     *
     * ⚠️ Сейчас это обычная переменная — UI не будет автоматически обновляться.
     * В идеале использовать State / MutableState / StateFlow.
     */
    var isLoading = false

    /**
     * Сообщение об ошибке.
     *
     * null — ошибки нет
     * иначе — текст ошибки (например, от API)
     *
     * ⚠️ Аналогично: не является реактивным состоянием.
     */
    var error: String? = null

    /**
     * Регистрация пользователя.
     *
     * @param nickname имя пользователя
     * @param password пароль
     * @param onSuccess вызывается при успешной регистрации
     */
    fun register(
        nickname: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        // Запускаем корутину в scope ViewModel
        viewModelScope.launch {

            // Включаем состояние загрузки
            isLoading = true

            // Выполняем запрос через Repository
            val result = repo.register(nickname, password)

            // Выключаем загрузку
            isLoading = false

            // Обрабатываем результат
            result
                .onSuccess {
                    // Успешная регистрация → вызываем колбэк UI
                    onSuccess()
                }
                .onFailure {
                    // Ошибка → сохраняем сообщение
                    error = it.message
                }
        }
    }

    /**
     * Авторизация пользователя.
     *
     * @param nickname имя пользователя
     * @param password пароль
     * @param onSuccess вызывается при успешном входе
     */
    fun login(
        nickname: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {

            // Включаем индикатор загрузки
            isLoading = true

            // Выполняем запрос
            val result = repo.login(nickname, password)

            // Выключаем индикатор
            isLoading = false

            // Обработка результата
            result
                .onSuccess {
                    onSuccess()
                }
                .onFailure {
                    error = it.message
                }
        }
    }
}