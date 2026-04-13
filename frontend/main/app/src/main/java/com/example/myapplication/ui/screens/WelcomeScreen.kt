package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.AuthGradientPage
import com.example.myapplication.ui.components.BrandChip
import com.example.myapplication.ui.components.PrimaryActionButton
import com.example.myapplication.ui.components.SecondaryActionButton
import com.example.myapplication.ui.components.WelcomeIllustration
import com.example.myapplication.ui.theme.Muted
import com.example.myapplication.ui.theme.WhiteSoft

/**
 * Приветственный экран приложения.
 *
 * Его задача:
 * - познакомить пользователя с приложением;
 * - показать фирменный стиль и короткое описание;
 * - дать два основных действия:
 *   регистрация и вход.
 *
 * Обычно это первый пользовательский экран после splash screen.
 */
@Composable
fun WelcomeScreen(
    // Колбэк перехода на экран регистрации.
    onRegisterClick: () -> Unit = {},

    // Колбэк перехода на экран входа.
    onLoginClick: () -> Unit = {}
) {
    // Базовый контейнер страницы с фирменным фоном.
    AuthGradientPage {
        Column(
            modifier = Modifier
                .fillMaxSize()

                // Добавляет безопасный верхний отступ под статус-бар.
                .statusBarsPadding()

                // Общие внутренние отступы экрана.
                .padding(horizontal = 24.dp, vertical = 24.dp),

            // Центрируем всё содержимое по горизонтали.
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Небольшой фирменный элемент/логотип в верхней части экрана.
            BrandChip()

            Spacer(modifier = Modifier.height(32.dp))

            // Основной заголовок экрана.
            Text(
                text = "Добро пожаловать\nв береZку",
                color = WhiteSoft,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 34.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Короткое описание ценности приложения.
            Text(
                text = "Легко общайтесь с друзьями и близкими.\nТепло, как под берёзой летом.",
                color = Muted,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(34.dp))

            // Иллюстрация приветственного экрана.
            // Обычно служит для визуального усиления первого впечатления.
            WelcomeIllustration()

            // Заполняющий spacer отталкивает кнопки вниз экрана,
            // сохраняя визуальный баланс между верхним контентом и CTA-блоком.
            Spacer(modifier = Modifier.weight(1f))

            // Основная CTA-кнопка: регистрация нового пользователя.
            PrimaryActionButton(
                text = "Создать аккаунт",
                onClick = onRegisterClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Вторичная кнопка: переход к экрану входа.
            SecondaryActionButton(
                text = "Войти",
                onClick = onLoginClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Небольшая брендовая подпись внизу экрана.
            Text(
                text = "береZка — место, где общение пускает корни",
                color = Muted,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Превью welcome-экрана для Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen()
    }
}