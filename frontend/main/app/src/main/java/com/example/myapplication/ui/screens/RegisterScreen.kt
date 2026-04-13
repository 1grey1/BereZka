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
 * Экран регистрации нового пользователя.
 *
 * Отвечает за:
 * - ввод имени пользователя и пароля;
 * - подтверждение пароля;
 * - принятие условий использования;
 * - локальную валидацию формы;
 * - вызов register() у AuthViewModel;
 * - отображение ошибок и состояния загрузки;
 * - переход на экран входа.
 */
@Composable
fun RegisterScreen(
    // Колбэк, который вызывается после успешной регистрации.
    onRegisterClick: () -> Unit = {},

    // Колбэк перехода на экран входа.
    onLoginClick: () -> Unit = {},

    // ViewModel, управляющая логикой авторизации/регистрации.
    authViewModel: AuthViewModel = viewModel()
) {
    // Имя пользователя, вводимое в форму.
    var username by rememberSaveable { mutableStateOf("") }

    // Основной пароль.
    var password by rememberSaveable { mutableStateOf("") }

    // Повторный ввод пароля для проверки совпадения.
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    // Флаг показа/скрытия текста пароля.
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Флаг показа/скрытия текста подтверждения пароля.
    var confirmVisible by rememberSaveable { mutableStateOf(false) }

    // Подтверждение принятия условий и политики.
    // Сейчас по умолчанию установлено в true.
    // Если по UX нужно явное согласие пользователя, лучше поменять на false.
    var accepted by rememberSaveable { mutableStateOf(true) }

    // Локальная ошибка валидации формы.
    // Используется для сообщений вроде "Введите пароль" или "Пароли не совпадают".
    var localError by remember { mutableStateOf<String?>(null) }

    // Ошибка, пришедшая из ViewModel / API.
    val apiError = authViewModel.error

    // Флаг выполнения запроса регистрации.
    val isLoading = authViewModel.isLoading

    // Базовый контейнер страницы с фоновым оформлением.
    AuthGradientPage {
        Column(
            modifier = Modifier
                .fillMaxWidth()

                // Отступ сверху с учётом системного статус-бара.
                .statusBarsPadding()

                // Поднимает контент над клавиатурой.
                .imePadding()

                // Даёт возможность прокрутки при небольшой высоте экрана.
                .verticalScroll(rememberScrollState())

                // Общий внутренний отступ страницы.
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Верхний бейдж с коротким приветственным текстом.
            ScreenBadge("Новый росток")

            Spacer(modifier = Modifier.height(16.dp))

            // Основная карточка с формой регистрации.
            GlassCard {
                Text(
                    text = "Регистрация",
                    color = WhiteSoft,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Описание смысла экрана.
                Text(
                    text = "Создайте аккаунт в береZке, чтобы начать общение в спокойном и живом пространстве.",
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

                        // При изменении значения очищаем локальную ошибку,
                        // чтобы сообщение не оставалось после исправления ввода.
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

                // Поле ввода основного пароля.
                AuthTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        localError = null
                    },
                    label = "Пароль",
                    placeholder = "Придумайте пароль",
                    leading = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = Muted
                        )
                    },
                    trailing = {
                        // Кнопка переключения видимости пароля.
                        TextButton(onClick = { passwordVisible = !passwordVisible }) {
                            Text(
                                text = if (passwordVisible) "Скрыть" else "Показать",
                                color = BerezkaGreenSoft,
                                fontSize = 12.sp
                            )
                        }
                    },

                    // Если флаг активен — пароль отображается открыто.
                    // Иначе скрывается стандартной трансформацией.
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Поле подтверждения пароля.
                AuthTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        localError = null
                    },
                    label = "Подтверждение пароля",
                    placeholder = "Повторите пароль",
                    leading = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = Muted
                        )
                    },
                    trailing = {
                        // Отдельный переключатель видимости для второго поля пароля.
                        TextButton(onClick = { confirmVisible = !confirmVisible }) {
                            Text(
                                text = if (confirmVisible) "Скрыть" else "Показать",
                                color = BerezkaGreenSoft,
                                fontSize = 12.sp
                            )
                        }
                    },
                    visualTransformation = if (confirmVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Блок принятия условий и политики приложения.
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = accepted,
                        onCheckedChange = {
                            accepted = it

                            // Сбрасываем локальную ошибку после изменения чекбокса.
                            localError = null
                        }
                    )

                    Text(
                        text = "Принимаю условия и политику береZки",
                        color = Muted,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Приоритет отдаётся локальной ошибке валидации.
                // Если её нет — показывается ошибка от API.
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

                // Пока идёт регистрация, показываем индикатор загрузки.
                if (isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Основная кнопка регистрации.
                PrimaryActionButton(
                    text = "Зарегистрироваться",
                    onClick = {
                        // Локальная проверка формы перед вызовом ViewModel.
                        when {
                            username.isBlank() -> {
                                localError = "Введите имя пользователя"
                            }

                            password.isBlank() -> {
                                localError = "Введите пароль"
                            }

                            confirmPassword.isBlank() -> {
                                localError = "Подтвердите пароль"
                            }

                            password != confirmPassword -> {
                                localError = "Пароли не совпадают"
                            }

                            !accepted -> {
                                localError = "Нужно принять условия и политику"
                            }

                            else -> {
                                // Если все проверки пройдены —
                                // очищаем локальную ошибку и отправляем запрос на регистрацию.
                                localError = null

                                authViewModel.register(
                                    // trim() убирает лишние пробелы по краям.
                                    nickname = username.trim(),
                                    password = password,

                                    // При успешной регистрации вызывается внешний колбэк.
                                    onSuccess = onRegisterClick
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Дополнительная подсказка пользователю по качеству пароля.
                Text(
                    text = "Пароль должен быть надёжным, как корни старой берёзы.",
                    color = Muted,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Нижний блок со ссылкой на экран входа.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Уже есть аккаунт? ",
                        color = Muted,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Войти",
                        color = BerezkaGreenSoft,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,

                        // Кликабельный текст для перехода на экран логина.
                        modifier = Modifier.clickable(onClick = onLoginClick)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

/**
 * Превью экрана регистрации для Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}