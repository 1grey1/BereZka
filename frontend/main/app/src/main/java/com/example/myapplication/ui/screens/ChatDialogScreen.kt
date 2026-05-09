package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.local.room.MessengerRoomDatabase
import com.example.myapplication.data.local.room.entity.MessageEntity
import com.example.myapplication.data.repository.ChatRepository
import com.example.myapplication.data.repository.MessageRepository
import com.example.myapplication.ui.theme.BerezkaGreen
import com.example.myapplication.ui.theme.WhiteSoft
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatDialogScreen(
    chatId: String,
    peerName: String,
    myUid: String = "debug_me",
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { MessengerRoomDatabase.getInstance(context) }
    val messageRepo = remember { MessageRepository(db.messageDao()) }
    val chatRepo = remember { ChatRepository(db.chatDao()) }

    var messages by remember { mutableStateOf<List<MessageEntity>>(emptyList()) }
    var input by remember { mutableStateOf("") }

    suspend fun refresh() {
        messages = messageRepo.listMessagesByChat(chatId)
    }

    fun sendMessage() {
        val text = input.trim()
        if (text.isEmpty()) return
        scope.launch {
            val now = System.currentTimeMillis()
            val chat = chatRepo.getChatById(chatId)
            messageRepo.insertMessage(
                MessageEntity(
                    messageId = "msg_$now",
                    chatId = chatId,
                    fromUid = myUid,
                    toUid = chat?.peerUid ?: "peer",
                    text = text,
                    createdAt = now
                )
            )
            chatRepo.updateChatPreview(chatId, text, now)
            input = ""
            refresh()
        }
    }

    LaunchedEffect(chatId) {
        chatRepo.markChatAsRead(chatId)
        refresh()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF0D0F14))
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF222329), Color(0xFF1B1C21))
                        )
                    )
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "←",
                    color = WhiteSoft,
                    fontSize = 24.sp,
                    modifier = Modifier.clickable(onClick = onBackClick)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = peerName,
                    color = WhiteSoft,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(messages) { msg ->
                    val isMine = msg.fromUid == myUid
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = if (isMine) Alignment.End else Alignment.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(22.dp))
                                .background(
                                    if (isMine) Color(0xFF38C665)
                                    else Color(0xFF08A755)
                                )
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = msg.text,
                                color = Color(0xFFF4FFF6),
                                fontSize = 16.sp
                            )
                        }
                        Text(
                            text = formatMessageTime(msg.createdAt),
                            color = Color(0xFF5F6269),
                            fontSize = 10.sp,
                            modifier = Modifier.padding(top = 2.dp, start = 4.dp, end = 4.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color(0xFFDDDDDD))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "☺", color = Color(0xFF5E5E5E), fontSize = 22.sp)
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    value = input,
                    onValueChange = { input = it },
                    placeholder = { Text("Сообщение", color = Color(0xFF5F5F5F)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xFF222222),
                        unfocusedTextColor = Color(0xFF222222),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0xFF1B1B1B)
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF38C665))
                        .clickable(onClick = { sendMessage() }),
                    contentAlignment = Alignment.Center
                ) {
                    Text("➤", color = Color.White, fontSize = 22.sp)
                }
            }
        }
    }
}

private fun formatMessageTime(timestamp: Long): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
}
