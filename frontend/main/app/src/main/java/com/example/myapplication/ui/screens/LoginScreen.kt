package com.example.myapplication.ui.screens
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.myapplication.ui.components.DividerText
import com.example.myapplication.ui.components.GlassCard
import com.example.myapplication.ui.components.PrimaryActionButton
import com.example.myapplication.ui.components.ScreenBadge
import com.example.myapplication.ui.components.SocialButton
import com.example.myapplication.ui.theme.BerezkaGreenSoft
import com.example.myapplication.ui.theme.Muted
import com.example.myapplication.ui.theme.WhiteSoft
import com.example.myapplication.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var rememberMe by rememberSaveable { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }

    val apiError = authViewModel.error
    val isLoading = authViewModel.isLoading

    AuthGradientPage {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            ScreenBadge("С возвращением")
            Spacer(modifier = Modifier.height(16.dp))

            GlassCard {
                Text(
                    text = "Вход",
                    color = WhiteSoft,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Вернитесь в береZку и продолжайте общение там, где остановились.",
                    color = Muted,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(modifier = Modifier.height(22.dp))

                AuthTextField(
                    value = username,
                    onValueChange = {
                        username = it
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

                AuthTextField(
                    value = password,
                    onValueChange = {
                        password = it
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
                        TextButton(onClick = { passwordVisible = !passwordVisible }) {
                            Text(
                                text = if (passwordVisible) "Скрыть" else "Показать",
                                color = BerezkaGreenSoft,
                                fontSize = 12.sp
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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

                    TextButton(onClick = onForgotPasswordClick) {
                        Text(
                            text = "Забыли пароль?",
                            color = BerezkaGreenSoft
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

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

                if (isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                PrimaryActionButton(
                    text = "Войти",
                    onClick = {
                        when {
                            username.isBlank() -> {
                                localError = "Введите имя пользователя"
                            }

                            password.isBlank() -> {
                                localError = "Введите пароль"
                            }

                            else -> {
                                localError = null
                                authViewModel.login(
                                    nickname = username.trim(),
                                    password = password,
                                    onSuccess = onLoginClick
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

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
                        modifier = Modifier.clickable(onClick = onRegisterClick)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}