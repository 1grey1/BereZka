//package com.example.myapplication
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScope
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScope
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.imePadding
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBarsPadding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.Lock
//import androidx.compose.material.icons.outlined.Person
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.blur
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.delay
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MaterialTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    BerezkaAuthApp()
//                }
//            }
//        }
//    }
//}
//
//private enum class AuthRoute {
//    Splash, Welcome, Register, Login
//}
//
//@Composable
//fun BerezkaAuthApp() {
//    var route by remember { mutableStateOf(AuthRoute.Splash) }
//
//    when (route) {
//        AuthRoute.Splash -> SplashScreen(
//            onFinished = { route = AuthRoute.Welcome }
//        )
//
//        AuthRoute.Welcome -> WelcomeScreen(
//            onRegisterClick = { route = AuthRoute.Register },
//            onLoginClick = { route = AuthRoute.Login }
//        )
//
//        AuthRoute.Register -> RegisterScreen(
//            onRegisterClick = { route = AuthRoute.Login },
//            onLoginClick = { route = AuthRoute.Login }
//        )
//
//        AuthRoute.Login -> LoginScreen(
//            onLoginClick = {},
//            onRegisterClick = { route = AuthRoute.Register },
//            onForgotPasswordClick = {}
//        )
//    }
//}
//
//private val BerezkaGreen = Color(0xFF39D353)
//private val BerezkaGreenDark = Color(0xFF1E8E3E)
//private val BerezkaGreenSoft = Color(0xFF8EF0A3)
//private val BerezkaLeaf = Color(0xFFBEECC7)
//private val DeepForest = Color(0xFF0B1810)
//private val Night = Color(0xFF0D1620)
//private val Night2 = Color(0xFF13202B)
//private val GlassBorder = Color(0x26FFFFFF)
//private val Muted = Color(0xFFB9C7D6)
//private val WhiteSoft = Color(0xFFF7FBFF)
//
//@Composable
//fun SplashScreen(
//    onFinished: () -> Unit = {}
//) {
//    LaunchedEffect(Unit) {
//        delay(1500)
//        onFinished()
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    listOf(Color(0xFFF4F6F3), Color(0xFFE9EEE8))
//                )
//            )
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(14.dp)
//                .clip(RoundedCornerShape(34.dp))
//                .background(Color(0xFFF1F2F0))
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 28.dp, vertical = 34.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Spacer(modifier = Modifier.weight(1f))
//
//                ZHeroMark()
//
//                Spacer(modifier = Modifier.weight(1f))
//
//                Text(
//                    text = "from",
//                    color = Color(0xFF7C7C7C),
//                    fontSize = 16.sp
//                )
//
//                Text(
//                    text = "береZка",
//                    fontSize = 32.sp,
//                    fontWeight = FontWeight.Medium,
//                    style = TextStyle(
//                        brush = Brush.horizontalGradient(
//                            colors = listOf(BerezkaGreen, BerezkaGreenDark)
//                        )
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(18.dp))
//            }
//        }
//    }
//}
//
//
//@Composable
//private fun ZHeroMark() {
//    Box(
//        modifier = Modifier.size(width = 180.dp, height = 150.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            val path = Path().apply {
//                moveTo(size.width * 0.18f, size.height * 0.18f)
//                quadraticTo(
//                    size.width * 0.55f,
//                    size.height * 0.08f,
//                    size.width * 0.77f,
//                    size.height * 0.18f
//                )
//                quadraticTo(
//                    size.width * 0.80f,
//                    size.height * 0.26f,
//                    size.width * 0.68f,
//                    size.height * 0.38f
//                )
//                lineTo(size.width * 0.45f, size.height * 0.62f)
//                quadraticTo(
//                    size.width * 0.22f,
//                    size.height * 0.82f,
//                    size.width * 0.32f,
//                    size.height * 0.86f
//                )
//                lineTo(size.width * 0.90f, size.height * 0.86f)
//                lineTo(size.width * 0.90f, size.height * 0.96f)
//                lineTo(size.width * 0.30f, size.height * 0.96f)
//                quadraticTo(
//                    size.width * 0.05f,
//                    size.height * 0.95f,
//                    size.width * 0.10f,
//                    size.height * 0.78f
//                )
//                quadraticTo(
//                    size.width * 0.12f,
//                    size.height * 0.68f,
//                    size.width * 0.30f,
//                    size.height * 0.54f
//                )
//                lineTo(size.width * 0.62f, size.height * 0.28f)
//                lineTo(size.width * 0.18f, size.height * 0.18f)
//                close()
//            }
//
//            drawPath(
//                path = path,
//                brush = Brush.linearGradient(
//                    colors = listOf(
//                        Color(0xFF18FF1E),
//                        Color(0xFF2FD14C),
//                        Color(0xFF1EA13A)
//                    )
//                )
//            )
//        }
//    }
//}
//
//@Composable
//fun WelcomeScreen(
//    onRegisterClick: () -> Unit = {},
//    onLoginClick: () -> Unit = {}
//) {
//    AuthGradientPage {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .padding(horizontal = 24.dp, vertical = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            BrandChip()
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Text(
//                text = "Добро пожаловать\nв береZку",
//                color = WhiteSoft,
//                fontSize = 30.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center,
//                lineHeight = 34.sp
//            )
//
//            Spacer(modifier = Modifier.height(14.dp))
//
//            Text(
//                text = "Легко общайтесь с друзьями и близкими.\nТепло, как под берёзой летом.",
//                color = Muted,
//                fontSize = 15.sp,
//                lineHeight = 22.sp,
//                textAlign = TextAlign.Center
//            )
//
//            Spacer(modifier = Modifier.height(34.dp))
//
//            WelcomeIllustration()
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            PrimaryActionButton(
//                text = "Создать аккаунт",
//                onClick = onRegisterClick
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            SecondaryActionButton(
//                text = "Войти",
//                onClick = onLoginClick
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = "береZка — место, где общение пускает корни",
//                color = Muted,
//                fontSize = 13.sp,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}
//
//@Composable
//fun RegisterScreen(
//    onRegisterClick: () -> Unit = {},
//    onLoginClick: () -> Unit = {}
//) {
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }
//    var confirmVisible by remember { mutableStateOf(false) }
//    var accepted by remember { mutableStateOf(true) }
//
//    AuthGradientPage {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .imePadding()
//                .verticalScroll(rememberScrollState())
//                .padding(20.dp)
//        ) {
//            Spacer(modifier = Modifier.height(12.dp))
//            ScreenBadge("Новый росток")
//            Spacer(modifier = Modifier.height(16.dp))
//
//            GlassCard {
//                Text(
//                    text = "Регистрация",
//                    color = WhiteSoft,
//                    fontSize = 28.sp,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Создайте аккаунт в береZке, чтобы начать общение в спокойном и живом пространстве.",
//                    color = Muted,
//                    fontSize = 14.sp,
//                    lineHeight = 21.sp
//                )
//
//                Spacer(modifier = Modifier.height(22.dp))
//
//                AuthTextField(
//                    value = username,
//                    onValueChange = { username = it },
//                    label = "Имя пользователя",
//                    placeholder = "Введите имя пользователя",
//                    leading = {
//                        Icon(
//                            imageVector = Icons.Outlined.Person,
//                            contentDescription = null,
//                            tint = Muted
//                        )
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(14.dp))
//
//                AuthTextField(
//                    value = password,
//                    onValueChange = { password = it },
//                    label = "Пароль",
//                    placeholder = "Придумайте пароль",
//                    leading = {
//                        Icon(
//                            imageVector = Icons.Outlined.Lock,
//                            contentDescription = null,
//                            tint = Muted
//                        )
//                    },
//                    trailing = {
//                        TextButton(onClick = { passwordVisible = !passwordVisible }) {
//                            Text(
//                                text = if (passwordVisible) "Скрыть" else "Показать",
//                                color = BerezkaGreenSoft,
//                                fontSize = 12.sp
//                            )
//                        }
//                    },
//                    visualTransformation = if (passwordVisible) {
//                        VisualTransformation.None
//                    } else {
//                        PasswordVisualTransformation()
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(14.dp))
//
//                AuthTextField(
//                    value = confirmPassword,
//                    onValueChange = { confirmPassword = it },
//                    label = "Подтверждение пароля",
//                    placeholder = "Повторите пароль",
//                    leading = {
//                        Icon(
//                            imageVector = Icons.Outlined.Lock,
//                            contentDescription = null,
//                            tint = Muted
//                        )
//                    },
//                    trailing = {
//                        TextButton(onClick = { confirmVisible = !confirmVisible }) {
//                            Text(
//                                text = if (confirmVisible) "Скрыть" else "Показать",
//                                color = BerezkaGreenSoft,
//                                fontSize = 12.sp
//                            )
//                        }
//                    },
//                    visualTransformation = if (confirmVisible) {
//                        VisualTransformation.None
//                    } else {
//                        PasswordVisualTransformation()
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Checkbox(
//                        checked = accepted,
//                        onCheckedChange = { accepted = it }
//                    )
//                    Text(
//                        text = "Принимаю условия и политику береZки",
//                        color = Muted,
//                        fontSize = 13.sp
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                PrimaryActionButton(
//                    text = "Зарегистрироваться",
//                    onClick = onRegisterClick
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Text(
//                    text = "Пароль должен быть надёжным, как корни старой берёзы.",
//                    color = Muted,
//                    fontSize = 12.sp,
//                    lineHeight = 18.sp
//                )
//
//                Spacer(modifier = Modifier.height(18.dp))
//
//                DividerText("или")
//
//                Spacer(modifier = Modifier.height(14.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    SocialButton(
//                        modifier = Modifier.weight(1f),
//                        text = "Google"
//                    )
//                    SocialButton(
//                        modifier = Modifier.weight(1f),
//                        text = "VK ID"
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Уже есть аккаунт? ",
//                        color = Muted,
//                        fontSize = 14.sp
//                    )
//                    Text(
//                        text = "Войти",
//                        color = BerezkaGreenSoft,
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        modifier = Modifier.clickable(onClick = onLoginClick)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//    }
//}
//
//@Composable
//fun LoginScreen(
//    onLoginClick: () -> Unit = {},
//    onRegisterClick: () -> Unit = {},
//    onForgotPasswordClick: () -> Unit = {}
//) {
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }
//    var rememberMe by remember { mutableStateOf(false) }
//
//    AuthGradientPage {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .imePadding()
//                .verticalScroll(rememberScrollState())
//                .padding(20.dp)
//        ) {
//            Spacer(modifier = Modifier.height(12.dp))
//            ScreenBadge("С возвращением")
//            Spacer(modifier = Modifier.height(16.dp))
//
//            GlassCard {
//                Text(
//                    text = "Вход",
//                    color = WhiteSoft,
//                    fontSize = 28.sp,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Вернитесь в береZку и продолжайте общение там, где остановились.",
//                    color = Muted,
//                    fontSize = 14.sp,
//                    lineHeight = 21.sp
//                )
//
//                Spacer(modifier = Modifier.height(22.dp))
//
//                AuthTextField(
//                    value = username,
//                    onValueChange = { username = it },
//                    label = "Имя пользователя",
//                    placeholder = "Введите имя пользователя",
//                    leading = {
//                        Icon(
//                            imageVector = Icons.Outlined.Person,
//                            contentDescription = null,
//                            tint = Muted
//                        )
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(14.dp))
//
//                AuthTextField(
//                    value = password,
//                    onValueChange = { password = it },
//                    label = "Пароль",
//                    placeholder = "Введите пароль",
//                    leading = {
//                        Icon(
//                            imageVector = Icons.Outlined.Lock,
//                            contentDescription = null,
//                            tint = Muted
//                        )
//                    },
//                    trailing = {
//                        TextButton(onClick = { passwordVisible = !passwordVisible }) {
//                            Text(
//                                text = if (passwordVisible) "Скрыть" else "Показать",
//                                color = BerezkaGreenSoft,
//                                fontSize = 12.sp
//                            )
//                        }
//                    },
//                    visualTransformation = if (passwordVisible) {
//                        VisualTransformation.None
//                    } else {
//                        PasswordVisualTransformation()
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Row(
//                        modifier = Modifier.weight(1f),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Checkbox(
//                            checked = rememberMe,
//                            onCheckedChange = { rememberMe = it }
//                        )
//                        Text(
//                            text = "Запомнить меня",
//                            color = Muted,
//                            fontSize = 13.sp
//                        )
//                    }
//
//                    TextButton(onClick = onForgotPasswordClick) {
//                        Text(
//                            text = "Забыли пароль?",
//                            color = BerezkaGreenSoft
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(6.dp))
//
//                PrimaryActionButton(
//                    text = "Войти",
//                    onClick = onLoginClick
//                )
//
//                Spacer(modifier = Modifier.height(18.dp))
//
//                DividerText("или")
//
//                Spacer(modifier = Modifier.height(14.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    SocialButton(
//                        modifier = Modifier.weight(1f),
//                        text = "Google"
//                    )
//                    SocialButton(
//                        modifier = Modifier.weight(1f),
//                        text = "VK ID"
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Нет аккаунта? ",
//                        color = Muted,
//                        fontSize = 14.sp
//                    )
//                    Text(
//                        text = "Зарегистрироваться",
//                        color = BerezkaGreenSoft,
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        modifier = Modifier.clickable(onClick = onRegisterClick)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//    }
//}
//
//@Composable
//private fun AuthGradientPage(
//    content: @Composable BoxScope.() -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    colors = listOf(Night2, Night)
//                )
//            )
//    ) {
//        Box(
//            modifier = Modifier.matchParentSize()
//        ) {
//            Box(
//                modifier = Modifier
//                    .offset(x = (-40).dp, y = 60.dp)
//                    .size(220.dp)
//                    .clip(CircleShape)
//                    .background(
//                        Brush.radialGradient(
//                            listOf(BerezkaGreen.copy(alpha = 0.28f), Color.Transparent)
//                        )
//                    )
//                    .blur(18.dp)
//            )
//
//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .offset(x = 70.dp, y = 40.dp)
//                    .size(260.dp)
//                    .clip(CircleShape)
//                    .background(
//                        Brush.radialGradient(
//                            listOf(Color(0xFF59BFFF).copy(alpha = 0.18f), Color.Transparent)
//                        )
//                    )
//                    .blur(22.dp)
//            )
//
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .offset(x = 20.dp, y = 80.dp)
//                    .size(260.dp)
//                    .clip(CircleShape)
//                    .background(
//                        Brush.radialGradient(
//                            listOf(BerezkaLeaf.copy(alpha = 0.16f), Color.Transparent)
//                        )
//                    )
//                    .blur(22.dp)
//            )
//        }
//
//        Canvas(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(240.dp)
//                .align(Alignment.TopCenter)
//                .offset(y = 110.dp)
//        ) {
//            val wave1 = Path().apply {
//                moveTo(0f, size.height * 0.55f)
//                cubicTo(
//                    size.width * 0.18f, size.height * 0.10f,
//                    size.width * 0.34f, size.height * 0.18f,
//                    size.width * 0.50f, size.height * 0.68f
//                )
//                cubicTo(
//                    size.width * 0.66f, size.height * 1.0f,
//                    size.width * 0.82f, size.height * 0.42f,
//                    size.width, size.height * 0.60f
//                )
//                lineTo(size.width, 0f)
//                lineTo(0f, 0f)
//                close()
//            }
//
//            val wave2 = Path().apply {
//                moveTo(0f, size.height * 0.70f)
//                cubicTo(
//                    size.width * 0.16f, size.height * 0.26f,
//                    size.width * 0.33f, size.height * 0.32f,
//                    size.width * 0.49f, size.height * 0.78f
//                )
//                cubicTo(
//                    size.width * 0.64f, size.height * 1.04f,
//                    size.width * 0.80f, size.height * 0.56f,
//                    size.width, size.height * 0.76f
//                )
//                lineTo(size.width, 0f)
//                lineTo(0f, 0f)
//                close()
//            }
//
//            drawPath(
//                path = wave1,
//                brush = SolidColor(BerezkaGreen.copy(alpha = 0.16f))
//            )
//            drawPath(
//                path = wave2,
//                brush = SolidColor(Color.White.copy(alpha = 0.08f))
//            )
//        }
//
//        content()
//    }
//}
//
//@Composable
//private fun BrandChip() {
//    Row(
//        modifier = Modifier
//            .clip(RoundedCornerShape(100.dp))
//            .background(Color.White.copy(alpha = 0.08f))
//            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(100.dp))
//            .padding(horizontal = 14.dp, vertical = 10.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .size(34.dp)
//                .clip(RoundedCornerShape(12.dp))
//                .background(
//                    Brush.linearGradient(
//                        colors = listOf(BerezkaGreenSoft, BerezkaGreen)
//                    )
//                )
//        ) {
//            Box(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .size(width = 18.dp, height = 3.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(DeepForest)
//            )
//            Box(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .size(width = 3.dp, height = 18.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(DeepForest)
//            )
//        }
//
//        Spacer(modifier = Modifier.width(10.dp))
//
//        Text(
//            text = "береZка",
//            color = WhiteSoft,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 15.sp
//        )
//    }
//}
//
//@Composable
//private fun WelcomeIllustration() {
//    Box(
//        modifier = Modifier.size(260.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .matchParentSize()
//                .padding(24.dp)
//                .clip(RoundedCornerShape(42.dp))
//                .background(Color.White.copy(alpha = 0.07f))
//                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(42.dp))
//        )
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(top = 18.dp, end = 4.dp)
//                .size(92.dp)
//                .clip(RoundedCornerShape(28.dp))
//                .background(Color.White.copy(alpha = 0.08f))
//                .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(28.dp))
//        )
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.CenterStart)
//                .offset(x = 20.dp, y = 16.dp)
//                .size(112.dp)
//                .clip(RoundedCornerShape(28.dp))
//                .background(
//                    Brush.linearGradient(
//                        listOf(Color(0xFF7EF3F0), BerezkaGreen)
//                    )
//                )
//        ) {
//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .offset(y = 18.dp)
//                    .size(36.dp)
//                    .clip(CircleShape)
//                    .background(Color.White.copy(alpha = 0.95f))
//            )
//
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .offset(y = (-16).dp)
//                    .size(width = 62.dp, height = 30.dp)
//                    .clip(RoundedCornerShape(20.dp))
//                    .background(Color.White.copy(alpha = 0.95f))
//            )
//        }
//
//        Column(
//            modifier = Modifier
//                .align(Alignment.CenterEnd)
//                .offset(x = (-12).dp, y = (-10).dp)
//        ) {
//            ChatLine(76.dp, Color.White.copy(alpha = 0.88f))
//            Spacer(modifier = Modifier.height(10.dp))
//            ChatLine(96.dp, Color.White.copy(alpha = 0.70f))
//            Spacer(modifier = Modifier.height(16.dp))
//            ChatLine(58.dp, BerezkaGreenSoft.copy(alpha = 0.95f))
//        }
//    }
//}
//
//@Composable
//private fun ChatLine(width: androidx.compose.ui.unit.Dp, color: Color) {
//    Box(
//        modifier = Modifier
//            .height(12.dp)
//            .width(width)
//            .clip(RoundedCornerShape(100.dp))
//            .background(color)
//    )
//}
//
//@Composable
//private fun ScreenBadge(text: String) {
//    Row(
//        modifier = Modifier
//            .clip(RoundedCornerShape(100.dp))
//            .background(BerezkaGreen.copy(alpha = 0.12f))
//            .border(1.dp, BerezkaGreen.copy(alpha = 0.22f), RoundedCornerShape(100.dp))
//            .padding(horizontal = 12.dp, vertical = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .size(8.dp)
//                .clip(CircleShape)
//                .background(BerezkaGreenSoft)
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(
//            text = text,
//            color = WhiteSoft,
//            fontSize = 13.sp,
//            fontWeight = FontWeight.SemiBold
//        )
//    }
//}
//
//@Composable
//private fun GlassCard(
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(28.dp))
//            .background(
//                Brush.verticalGradient(
//                    listOf(
//                        Color.White.copy(alpha = 0.14f),
//                        Color.White.copy(alpha = 0.08f)
//                    )
//                )
//            )
//            .border(1.dp, GlassBorder, RoundedCornerShape(28.dp))
//            .padding(22.dp),
//        content = content
//    )
//}
//
//@Composable
//private fun AuthTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    placeholder: String,
//    leading: @Composable (() -> Unit)? = null,
//    trailing: @Composable (() -> Unit)? = null,
//    visualTransformation: VisualTransformation = VisualTransformation.None
//) {
//    Column {
//        Text(
//            text = label,
//            color = WhiteSoft,
//            fontSize = 13.sp,
//            fontWeight = FontWeight.SemiBold
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = value,
//            onValueChange = onValueChange,
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true,
//            textStyle = TextStyle(color = WhiteSoft, fontSize = 15.sp),
//            placeholder = {
//                Text(
//                    text = placeholder,
//                    color = Muted,
//                    fontSize = 15.sp
//                )
//            },
//            leadingIcon = leading,
//            trailingIcon = trailing,
//            visualTransformation = visualTransformation,
//            shape = RoundedCornerShape(16.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor = WhiteSoft,
//                unfocusedTextColor = WhiteSoft,
//                cursorColor = BerezkaGreenSoft,
//                focusedBorderColor = BerezkaGreen.copy(alpha = 0.75f),
//                unfocusedBorderColor = Color.White.copy(alpha = 0.12f),
//                focusedContainerColor = Color.White.copy(alpha = 0.08f),
//                unfocusedContainerColor = Color.White.copy(alpha = 0.06f),
//                focusedLeadingIconColor = BerezkaGreenSoft,
//                unfocusedLeadingIconColor = Muted,
//                focusedTrailingIconColor = BerezkaGreenSoft,
//                unfocusedTrailingIconColor = Muted
//            )
//        )
//    }
//}
//
//@Composable
//private fun PrimaryActionButton(
//    text: String,
//    onClick: () -> Unit
//) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(54.dp),
//        shape = RoundedCornerShape(18.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Transparent
//        ),
//        contentPadding = PaddingValues(0.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    Brush.verticalGradient(
//                        listOf(Color(0xFF79F59B), Color(0xFF48D86D))
//                    )
//                ),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = text,
//                color = DeepForest,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp
//            )
//        }
//    }
//}
//
//@Composable
//private fun SecondaryActionButton(
//    text: String,
//    onClick: () -> Unit
//) {
//    OutlinedButton(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(54.dp),
//        shape = RoundedCornerShape(18.dp),
//        border = BorderStroke(
//            1.dp,
//            Color.White.copy(alpha = 0.12f)
//        ),
//        colors = ButtonDefaults.outlinedButtonColors(
//            containerColor = Color.White.copy(alpha = 0.08f),
//            contentColor = WhiteSoft
//        )
//    ) {
//        Text(
//            text = text,
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 16.sp
//        )
//    }
//}
//
//@Composable
//private fun DividerText(text: String) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .weight(1f)
//                .height(1.dp)
//                .background(Color.White.copy(alpha = 0.12f))
//        )
//        Text(
//            text = "  $text  ",
//            color = Muted,
//            fontSize = 13.sp
//        )
//        Box(
//            modifier = Modifier
//                .weight(1f)
//                .height(1.dp)
//                .background(Color.White.copy(alpha = 0.12f))
//        )
//    }
//}
//
//@Composable
//private fun SocialButton(
//    modifier: Modifier = Modifier,
//    text: String
//) {
//    OutlinedButton(
//        onClick = {},
//        modifier = modifier.height(48.dp),
//        shape = RoundedCornerShape(14.dp),
//        border = BorderStroke(
//            1.dp,
//            Color.White.copy(alpha = 0.12f)
//        ),
//        colors = ButtonDefaults.outlinedButtonColors(
//            containerColor = Color.White.copy(alpha = 0.06f),
//            contentColor = WhiteSoft
//        )
//    ) {
//        Text(
//            text = text,
//            fontWeight = FontWeight.SemiBold
//        )
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SplashScreenPreview() {
//    MaterialTheme {
//        SplashScreen(onFinished = {})
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun WelcomeScreenPreview() {
//    MaterialTheme {
//        WelcomeScreen()
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun RegisterScreenPreview() {
//    MaterialTheme {
//        RegisterScreen()
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    MaterialTheme {
//        LoginScreen()
//    }
//}