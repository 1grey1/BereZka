package com.example.myapplication.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.BerezkaGreen
import com.example.myapplication.ui.theme.BerezkaGreenDark
import kotlinx.coroutines.delay

/**
 * Splash-экран приложения.
 *
 * Назначение экрана:
 * - показать стартовый брендированный экран;
 * - выдержать небольшую паузу перед переходом дальше;
 * - после задержки вызвать onFinished().
 *
 * Обычно используется как первый экран при запуске приложения.
 */
@Composable
fun SplashScreen(
    // Колбэк вызывается после завершения splash-экрана.
    // Обычно здесь происходит переход на следующий экран:
    // например, onboarding, login или main screen.
    onFinished: () -> Unit = {}
) {
    // Запускаем побочный эффект один раз при первой композиции экрана.
    // Через 1500 мс выполняется переход дальше.
    LaunchedEffect(Unit) {
        delay(1500)
        onFinished()
    }

    // Корневой контейнер splash-экрана на весь экран.
    // Здесь задаётся мягкий вертикальный градиент фона.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF4F6F3), Color(0xFFE9EEE8))
                )
            )
    ) {
        // Внутренний декоративный контейнер с отступом от краёв,
        // скруглением и светлой подложкой.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .clip(RoundedCornerShape(34.dp))
                .background(Color(0xFFF1F2F0))
        ) {
            // Основная вертикальная раскладка содержимого splash-экрана.
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp, vertical = 34.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Верхний гибкий отступ:
                // помогает визуально центрировать логотип по вертикали.
                Spacer(modifier = Modifier.weight(1f))

                // Кастомный логотип/знак бренда.
                ZHeroMark()

                // Нижний гибкий отступ:
                // балансирует логотип между верхом и нижней подписью.
                Spacer(modifier = Modifier.weight(1f))

                // Небольшая подпись перед названием/источником.
                Text(
                    text = "from",
                    color = Color(0xFF7C7C7C),
                    fontSize = 16.sp
                )

                // Название "MAI" с градиентной заливкой текста.
                Text(
                    text = "MAI",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    style = TextStyle(
                        brush = Brush.horizontalGradient(
                            colors = listOf(BerezkaGreen, BerezkaGreenDark)
                        )
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

/**
 * Кастомная векторная метка/логотип в форме стилизованной буквы Z.
 *
 * Рисуется вручную через Canvas и Path.
 * Размер контейнера фиксированный, а сам Path строится в процентах
 * от текущего размера Canvas, поэтому масштабируется вместе с ним.
 */
@Composable
private fun ZHeroMark() {
    Box(
        modifier = Modifier.size(width = 180.dp, height = 150.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            // Описываем контур фигуры вручную.
            // Здесь используются:
            // - moveTo(...) для стартовой точки,
            // - quadraticTo(...) для плавных кривых,
            // - lineTo(...) для прямых сегментов.
            val path = Path().apply {
                moveTo(size.width * 0.18f, size.height * 0.18f)

                quadraticTo(
                    size.width * 0.55f,
                    size.height * 0.08f,
                    size.width * 0.77f,
                    size.height * 0.18f
                )

                quadraticTo(
                    size.width * 0.80f,
                    size.height * 0.26f,
                    size.width * 0.68f,
                    size.height * 0.38f
                )

                lineTo(size.width * 0.45f, size.height * 0.62f)

                quadraticTo(
                    size.width * 0.22f,
                    size.height * 0.82f,
                    size.width * 0.32f,
                    size.height * 0.86f
                )

                lineTo(size.width * 0.90f, size.height * 0.86f)
                lineTo(size.width * 0.90f, size.height * 0.96f)
                lineTo(size.width * 0.30f, size.height * 0.96f)

                quadraticTo(
                    size.width * 0.05f,
                    size.height * 0.95f,
                    size.width * 0.10f,
                    size.height * 0.78f
                )

                quadraticTo(
                    size.width * 0.12f,
                    size.height * 0.68f,
                    size.width * 0.30f,
                    size.height * 0.54f
                )

                lineTo(size.width * 0.62f, size.height * 0.28f)
                lineTo(size.width * 0.18f, size.height * 0.18f)

                // Замыкаем контур фигуры.
                close()
            }

            // Рисуем готовую форму с линейным зелёным градиентом.
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF18FF1E),
                        Color(0xFF2FD14C),
                        Color(0xFF1EA13A)
                    )
                )
            )
        }
    }
}

/**
 * Превью splash-экрана для Android Studio.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MaterialTheme {
        SplashScreen()
    }
}