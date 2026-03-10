package com.example.berezka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen() {
    val screenBg = Color(0xFFF3F3F3)
    val green = Color(0xFF28C63F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBg)
    ) {
        TopWaveHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp),
            backgroundColor = screenBg
        )

        BottomDecorLines(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .align(Alignment.BottomCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(72.dp))

            Text(
                text = "РЕГИСТРАЦИЯ",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(280.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RegisterTextField("ИМЯ ПОЛЬЗОВАТЕЛЯ")
                RegisterTextField("ПАРОЛЬ")
                RegisterTextField("ПОДТВЕРЖДЕНИЕ ПАРОЛЯ")

                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = green,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "зарегистрироваться",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun TopWaveHeader(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFF3F3F3)
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1BFF13),
                    Color(0xFF2A8E49)
                )
            ),
            size = Size(w, h)
        )

        val backWave = Path().apply {
            moveTo(0f, h * 0.63f)

            cubicTo(
                w * 0.08f, h * 0.34f,
                w * 0.16f, h * 0.18f,
                w * 0.28f, h * 0.36f
            )

            cubicTo(
                w * 0.42f, h * 0.58f,
                w * 0.54f, h * 0.93f,
                w * 0.70f, h * 0.80f
            )

            cubicTo(
                w * 0.82f, h * 0.70f,
                w * 0.90f, h * 0.50f,
                w, h * 0.56f
            )

            lineTo(w, h)
            lineTo(0f, h)
            close()
        }

        drawPath(
            path = backWave,
            color = Color(0xFFD3E9D8).copy(alpha = 0.9f)
        )

        val frontWave = Path().apply {
            moveTo(0f, h * 0.72f)

            cubicTo(
                w * 0.08f, h * 0.47f,
                w * 0.16f, h * 0.22f,
                w * 0.28f, h * 0.42f
            )

            cubicTo(
                w * 0.42f, h * 0.66f,
                w * 0.56f, h * 1.02f,
                w * 0.72f, h * 0.88f
            )

            cubicTo(
                w * 0.84f, h * 0.78f,
                w * 0.92f, h * 0.62f,
                w, h * 0.68f
            )

            lineTo(w, h)
            lineTo(0f, h)
            close()
        }

        drawPath(
            path = frontWave,
            color = backgroundColor
        )
    }
}

@Composable
fun BottomDecorLines(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val lineLightTop = Path().apply {
            moveTo(0f, h * 0.42f)
            cubicTo(
                w * 0.16f, h * 0.30f,
                w * 0.30f, h * 0.22f,
                w * 0.46f, h * 0.44f
            )
            cubicTo(
                w * 0.60f, h * 0.62f,
                w * 0.74f, h * 0.14f,
                w, h * 0.02f
            )
        }

        drawPath(
            path = lineLightTop,
            color = Color(0xFF3ACB4D).copy(alpha = 0.35f),
            style = Stroke(width = 4.dp.toPx())
        )

        val lineMain = Path().apply {
            moveTo(0f, h * 0.78f)
            cubicTo(
                w * 0.10f, h * 0.52f,
                w * 0.22f, h * 0.40f,
                w * 0.38f, h * 0.72f
            )
            cubicTo(
                w * 0.46f, h * 0.92f,
                w * 0.50f, h * 0.06f,
                w * 0.64f, h * 0.08f
            )
            cubicTo(
                w * 0.76f, h * 0.10f,
                w * 0.78f, h * 0.95f,
                w, h * 0.80f
            )
        }

        drawPath(
            path = lineMain,
            color = Color(0xFF23C937),
            style = Stroke(width = 5.dp.toPx())
        )

        val lineBottom = Path().apply {
            moveTo(0f, h * 0.90f)
            cubicTo(
                w * 0.14f, h * 0.66f,
                w * 0.26f, h * 0.48f,
                w * 0.40f, h * 0.74f
            )
            cubicTo(
                w * 0.54f, h * 0.98f,
                w * 0.64f, h * 0.64f,
                w * 0.82f, h * 0.76f
            )
            cubicTo(
                w * 0.90f, h * 0.80f,
                w * 0.96f, h * 0.78f,
                w, h * 0.79f
            )
        }

        drawPath(
            path = lineBottom,
            color = Color(0xFF44CC56).copy(alpha = 0.8f),
            style = Stroke(width = 4.dp.toPx())
        )
    }
}

@Composable
fun RegisterTextField(hint: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                color = Color(0xFFD4D4D4),
                fontSize = 14.sp
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFFEFEFEF),
            focusedContainerColor = Color(0xFFEFEFEF),
            disabledContainerColor = Color(0xFFEFEFEF),
            focusedTextColor = Color(0xFF222222),
            unfocusedTextColor = Color(0xFF222222),
            cursorColor = Color(0xFF28C63F)
        )
    )
}