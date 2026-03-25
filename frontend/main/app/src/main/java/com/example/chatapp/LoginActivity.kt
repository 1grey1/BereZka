package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(
                onRegisterClick = {
                    startActivity(Intent(this, RegisterActivity::class.java))
                },
                onLoginClick = {
                    startActivity(Intent(this, LoginComposeActivity::class.java))
                }
            )
        }
    }
}
@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // Картинка (добавим ниже)
        Image(
            painter = painterResource(id = R.drawable.chat_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Text(
            text = "Легко общайтесь со своей семьей и друзьями из разных стран",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,     // жирный текст
            textAlign = TextAlign.Center,     // выравнивание по центру
            color = Color.DarkGray,
            lineHeight = 42.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()              // обязательно для центрирования
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onRegisterClick,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF34C759)
                )
            ) {
                Text("РЕГИСТРАЦИЯ")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLoginClick,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF34C759)
                )
            ) {
                Text("ВХОД")
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}