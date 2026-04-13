package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.BerezkaGreen
import com.example.myapplication.ui.theme.BerezkaGreenSoft
import com.example.myapplication.ui.theme.BerezkaLeaf
import com.example.myapplication.ui.theme.DeepForest
import com.example.myapplication.ui.theme.GlassBorder
import com.example.myapplication.ui.theme.Muted
import com.example.myapplication.ui.theme.Night
import com.example.myapplication.ui.theme.Night2
import com.example.myapplication.ui.theme.WhiteSoft

/**
 * Базовый фон для auth-экранов.
 *
 * Используется как контейнер верхнего уровня для:
 * - welcome screen
 * - login screen
 * - register screen
 *
 * Внутри рисуются:
 * - основной тёмный градиентный фон;
 * - декоративные размытые цветовые пятна;
 * - волнообразные формы сверху;
 * - пользовательский content.
 */
@Composable
fun AuthGradientPage(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Night2, Night)
                )
            )
    ) {
        /**
         * Декоративный слой с мягкими размытыми кругами.
         *
         * Эти элементы не несут функциональной нагрузки,
         * а только формируют фирменный визуальный стиль.
         */
        Box(modifier = Modifier.matchParentSize()) {

            // Левое верхнее зелёное свечение
            Box(
                modifier = Modifier
                    .offset(x = (-40).dp, y = 60.dp)
                    .size(220.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(BerezkaGreen.copy(alpha = 0.28f), Color.Transparent)
                        )
                    )
                    .blur(18.dp)
            )

            // Правое верхнее голубоватое свечение
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 70.dp, y = 40.dp)
                    .size(260.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(Color(0xFF59BFFF).copy(alpha = 0.18f), Color.Transparent)
                        )
                    )
                    .blur(22.dp)
            )

            // Левое нижнее светло-зелёное свечение
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 20.dp, y = 80.dp)
                    .size(260.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(BerezkaLeaf.copy(alpha = 0.16f), Color.Transparent)
                        )
                    )
                    .blur(22.dp)
            )
        }

        /**
         * Декоративные волны в верхней части экрана.
         *
         * Рисуются вручную через Canvas и Path.
         * Используются только для оформления.
         */
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .align(Alignment.TopCenter)
                .offset(y = 110.dp)
        ) {
            // Первая волна
            val wave1 = Path().apply {
                moveTo(0f, size.height * 0.55f)
                cubicTo(
                    size.width * 0.18f, size.height * 0.10f,
                    size.width * 0.34f, size.height * 0.18f,
                    size.width * 0.50f, size.height * 0.68f
                )
                cubicTo(
                    size.width * 0.66f, size.height * 1.0f,
                    size.width * 0.82f, size.height * 0.42f,
                    size.width, size.height * 0.60f
                )
                lineTo(size.width, 0f)
                lineTo(0f, 0f)
                close()
            }

            // Вторая волна поверх первой
            val wave2 = Path().apply {
                moveTo(0f, size.height * 0.70f)
                cubicTo(
                    size.width * 0.16f, size.height * 0.26f,
                    size.width * 0.33f, size.height * 0.32f,
                    size.width * 0.49f, size.height * 0.78f
                )
                cubicTo(
                    size.width * 0.64f, size.height * 1.04f,
                    size.width * 0.80f, size.height * 0.56f,
                    size.width, size.height * 0.76f
                )
                lineTo(size.width, 0f)
                lineTo(0f, 0f)
                close()
            }

            drawPath(
                path = wave1,
                brush = SolidColor(BerezkaGreen.copy(alpha = 0.16f))
            )

            drawPath(
                path = wave2,
                brush = SolidColor(Color.White.copy(alpha = 0.08f))
            )
        }

        // Содержимое конкретного экрана, переданное снаружи.
        content()
    }
}

/**
 * Небольшой брендовый элемент с названием приложения.
 *
 * Обычно показывается в верхней части приветственного экрана.
 * Состоит из:
 * - декоративной иконки;
 * - названия "береZка".
 */
@Composable
fun BrandChip() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(100.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        /**
         * Левый декоративный знак.
         *
         * Внутри собран из двух прямоугольников,
         * образующих простую геометрическую форму.
         */
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(BerezkaGreenSoft, BerezkaGreen)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 18.dp, height = 3.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DeepForest)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 3.dp, height = 18.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DeepForest)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "береZка",
            color = WhiteSoft,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}

/**
 * Декоративная иллюстрация для welcome-экрана.
 *
 * Не содержит интерактивной логики.
 * Состоит из нескольких блоков:
 * - фоновая карточка;
 * - верхний декоративный элемент;
 * - центральная карточка с условным аватаром;
 * - справа набор "чат-линий".
 */
@Composable
fun WelcomeIllustration() {
    Box(
        modifier = Modifier.size(260.dp),
        contentAlignment = Alignment.Center
    ) {
        // Основная стеклянная подложка
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(24.dp)
                .clip(RoundedCornerShape(42.dp))
                .background(Color.White.copy(alpha = 0.07f))
                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(42.dp))
        )

        // Верхний правый декоративный блок
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 18.dp, end = 4.dp)
                .size(92.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White.copy(alpha = 0.08f))
                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(28.dp))
        )

        /**
         * Центральный левый акцентный блок.
         *
         * Внутри схематично изображён человек/аватар:
         * - круг сверху как голова;
         * - округлый блок снизу как туловище.
         */
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 20.dp, y = 16.dp)
                .size(112.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF7EF3F0), BerezkaGreen)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 18.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.95f))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-16).dp)
                    .size(width = 62.dp, height = 30.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.95f))
            )
        }

        /**
         * Справа размещается набор декоративных горизонтальных линий,
         * имитирующих сообщения или элементы интерфейса чата.
         */
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-12).dp, y = (-10).dp)
        ) {
            ChatLine(76.dp, Color.White.copy(alpha = 0.88f))
            Spacer(modifier = Modifier.height(10.dp))
            ChatLine(96.dp, Color.White.copy(alpha = 0.70f))
            Spacer(modifier = Modifier.height(16.dp))
            ChatLine(58.dp, BerezkaGreenSoft.copy(alpha = 0.95f))
        }
    }
}

/**
 * Примитивный элемент в виде короткой горизонтальной полосы.
 *
 * Используется внутри WelcomeIllustration
 * как имитация строк текста/сообщений.
 */
@Composable
fun ChatLine(width: Dp, color: Color) {
    Box(
        modifier = Modifier
            .height(12.dp)
            .width(width)
            .clip(RoundedCornerShape(100.dp))
            .background(color)
    )
}

/**
 * Небольшой бейдж для экранов авторизации.
 *
 * Обычно используется как короткий статусный заголовок
 * над основной карточкой, например:
 * - "С возвращением"
 * - "Новый росток"
 */
@Composable
fun ScreenBadge(text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(BerezkaGreen.copy(alpha = 0.12f))
            .border(1.dp, BerezkaGreen.copy(alpha = 0.22f), RoundedCornerShape(100.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Маленькая точка-индикатор слева
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(BerezkaGreenSoft)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = WhiteSoft,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Универсальная "стеклянная" карточка для форм.
 *
 * Используется как контейнер для:
 * - login form
 * - register form
 * - других auth-блоков
 *
 * Содержимое передаётся через ColumnScope.
 */
@Composable
fun GlassCard(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White.copy(alpha = 0.14f),
                        Color.White.copy(alpha = 0.08f)
                    )
                )
            )
            .border(1.dp, GlassBorder, RoundedCornerShape(28.dp))
            .padding(22.dp),
        content = content
    )
}

/**
 * Кастомное текстовое поле для auth-экранов.
 *
 * Оборачивает OutlinedTextField и задаёт общий стиль:
 * - label над полем;
 * - placeholder;
 * - поддержку leading/trailing иконок;
 * - цвета и скругления;
 * - поддержку скрытия/отображения пароля через visualTransformation.
 */
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column {
        // Подпись над полем
        Text(
            text = label,
            color = WhiteSoft,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = WhiteSoft, fontSize = 15.sp),

            // Текст-подсказка внутри пустого поля
            placeholder = {
                Text(
                    text = placeholder,
                    color = Muted,
                    fontSize = 15.sp
                )
            },

            // Иконка или кастомный composable слева
            leadingIcon = leading,

            // Иконка или composable справа
            // например, "Показать / Скрыть" пароль
            trailingIcon = trailing,

            // Трансформация текста:
            // используется для скрытия пароля
            visualTransformation = visualTransformation,

            shape = RoundedCornerShape(16.dp),

            // Общая цветовая схема поля в фокусе и без фокуса
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = WhiteSoft,
                unfocusedTextColor = WhiteSoft,
                cursorColor = BerezkaGreenSoft,
                focusedBorderColor = BerezkaGreen.copy(alpha = 0.75f),
                unfocusedBorderColor = Color.White.copy(alpha = 0.12f),
                focusedContainerColor = Color.White.copy(alpha = 0.08f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.06f),
                focusedLeadingIconColor = BerezkaGreenSoft,
                unfocusedLeadingIconColor = Muted,
                focusedTrailingIconColor = BerezkaGreenSoft,
                unfocusedTrailingIconColor = Muted
            )
        )
    }
}

/**
 * Основная action-кнопка.
 *
 * Используется для ключевых действий экрана:
 * - "Войти"
 * - "Зарегистрироваться"
 *
 * Содержит собственный зелёный градиентный фон.
 */
@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(18.dp),

        // Контейнер самой Button делаем прозрачным,
        // потому что реальный фон рисуем вручную внутри Box.
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),

        // Убираем стандартные внутренние отступы,
        // чтобы Box занял всю площадь кнопки.
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF79F59B), Color(0xFF48D86D))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = DeepForest,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

/**
 * Вторичная action-кнопка.
 *
 * Используется для действий меньшего приоритета,
 * например перехода на логин с welcome-экрана.
 */
@Composable
fun SecondaryActionButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(
            1.dp,
            Color.White.copy(alpha = 0.12f)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White.copy(alpha = 0.08f),
            contentColor = WhiteSoft
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}
