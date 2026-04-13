package com.example.myapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.components.AuthGradientPage
import com.example.myapplication.ui.components.AuthTextField
import com.example.myapplication.ui.components.GlassCard
import com.example.myapplication.ui.components.PrimaryActionButton
import com.example.myapplication.ui.components.ScreenBadge
import com.example.myapplication.ui.theme.BerezkaGreenSoft
import com.example.myapplication.ui.theme.Muted
import com.example.myapplication.ui.theme.WhiteSoft
import com.example.myapplication.viewmodel.AuthViewModel

/**
 * Экран авторизации пользователя.
 *
 * Отвечает за:
 * - отображение формы входа;
 * - локальную валидацию полей;
 * - вызов login() у AuthViewModel;
 * - отображение загрузки и ошибок;
 * - переходы на регистрацию и восстановление пароля.
 */
@Composable
fun LoginScreen(
    // Колбэк успешного входа
    onLoginClick: () -> Unit = {},

    // Колбэк перехода на экран регистрации
    onRegisterClick: () -> Unit = {},

    // Колбэк перехода на экран восстановления пароля
    onForgotPasswordClick: () -> Unit = {},

    // ViewModel авторизации; по умолчанию получаем через viewModel()
    authViewModel: AuthViewModel = viewModel()
) {
    // Локальное состояние поля имени пользователя.
    // rememberSaveable сохраняет значение при пересоздании конфигурации
    // (например, поворот экрана).
    var username by rememberSaveable { mutableStateOf("") }

    // Локальное состояние поля пароля.
    var password by rememberSaveable { mutableStateOf("") }

    // Флаг отображения/скрытия пароля.
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Флаг чекбокса "Запомнить меня".
    // Сейчас используется только на UI-уровне.
    // При необходимости можно передать его во ViewModel / DataStore.
    var rememberMe by rememberSaveable { mutableStateOf(false) }

    // Локальная ошибка валидации формы.
    // Например: пустой логин или пустой пароль.
    var localError by remember { mutableStateOf<String?>(null) }

    // Ошибка, пришедшая из ViewModel / API.
    val apiError = authViewModel.error

    // Флаг загрузки во время запроса авторизации.
    val isLoading = authViewModel.isLoading

    // Базовый контейнер страницы с градиентным фоном.
    AuthGradientPage {
        Column(
            modifier = Modifier
                .fillMaxWidth()

                // Добавляет верхний отступ под статус-бар.
                .statusBarsPadding()

                // Поднимает контент при открытии клавиатуры.
                .imePadding()

                // Позволяет прокручивать экран, если по высоте всё не помещается.
                .verticalScroll(rememberScrollState())

                // Внешний отступ основного содержимого.
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Небольшой верхний бейдж.
            ScreenBadge("С возвращением")

            Spacer(modifier = Modifier.height(16.dp))

            // Основная карточка с формой логина.
            GlassCard {
                Text(
                    text = "Вход",
                    color = WhiteSoft,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Подзаголовок / описание экрана.
                Text(
                    text = "Вернитесь в береZку и продолжайте общение там, где остановились.",
                    color = Muted,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Поле ввода имени пользователя.
                AuthTextField(
                    value = username,
                    onValueChange = {
                        username = it

                        // При изменении текста очищаем локальную ошибку,
                        // чтобы пользователь сразу видел актуальное состояние формы.
                        localError = null
                    },
                    label = "Имя пользователя",
                    placeholder = "Введите имя пользователя",
                    leading = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = Muted
                        )
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Поле ввода пароля.
                AuthTextField(
                    value = password,
                    onValueChange = {
                        password = it

                        // Сбрасываем локальную ошибку после редактирования поля.
                        localError = null
                    },
                    label = "Пароль",
                    placeholder = "Введите пароль",
                    leading = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = Muted
                        )
                    },
                    trailing = {
                        // Кнопка показа / скрытия пароля.
                        TextButton(onClick = { passwordVisible = !passwordVisible }) {
                            Text(
                                text = if (passwordVisible) "Скрыть" else "Показать",
                                color = BerezkaGreenSoft,
                                fontSize = 12.sp
                            )
                        }
                    },

                    // Если passwordVisible == true, показываем пароль как обычный текст.
                    // Иначе маскируем символы.
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Строка с чекбоксом "Запомнить меня" и ссылкой "Забыли пароль?"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Левая часть строки занимает всё доступное пространство.
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it }
                        )

                        Text(
                            text = "Запомнить меня",
                            color = Muted,
                            fontSize = 13.sp
                        )
                    }

                    // Переход к сценарию восстановления пароля.
                    TextButton(onClick = onForgotPasswordClick) {
                        Text(
                            text = "Забыли пароль?",
                            color = BerezkaGreenSoft
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Показываем либо локальную ошибку валидации,
                // либо ошибку, полученную из API.
                val errorText = localError ?: apiError

                if (!errorText.isNullOrBlank()) {
                    Text(
                        text = errorText,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Во время выполнения авторизации показываем индикатор загрузки.
                if (isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Основная кнопка входа.
                PrimaryActionButton(
                    text = "Войти",
                    onClick = {
                        // Простая локальная валидация перед отправкой запроса.
                        when {
                            username.isBlank() -> {
                                localError = "Введите имя пользователя"
                            }

                            password.isBlank() -> {
                                localError = "Введите пароль"
                            }

                            else -> {
                                // Если поля заполнены — очищаем локальную ошибку
                                // и запускаем авторизацию через ViewModel.
                                localError = null

                                authViewModel.login(
                                    // trim() убирает случайные пробелы по краям логина.
                                    nickname = username.trim(),
                                    password = password,

                                    // При успешной авторизации вызывается переданный колбэк.
                                    onSuccess = onLoginClick
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Нижний блок с предложением перейти к регистрации.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Нет аккаунта? ",
                        color = Muted,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Зарегистрироваться",
                        color = BerezkaGreenSoft,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,

                        // Текст сделан кликабельным и ведёт на экран регистрации.
                        modifier = Modifier.clickable(onClick = onRegisterClick)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

/**
 * Превью экрана логина в Android Studio.
 *
 * Удобно для быстрой проверки визуала без запуска приложения.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}