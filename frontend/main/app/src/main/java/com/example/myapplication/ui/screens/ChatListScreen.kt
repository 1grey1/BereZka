package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.local.room.MessengerRoomDatabase
import com.example.myapplication.data.local.room.entity.ChatEntity
import com.example.myapplication.data.repository.ChatRepository
import com.example.myapplication.ui.theme.BerezkaGreen
import com.example.myapplication.ui.theme.Muted
import com.example.myapplication.ui.theme.WhiteSoft
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ChatListScreen(
    myUid: String = "debug_me",
    onChatClick: (String, String) -> Unit,
    onBackToWelcome: () -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { MessengerRoomDatabase.getInstance(context) }
    val repo = remember { ChatRepository(db.chatDao()) }

    var chats by remember { mutableStateOf<List<ChatEntity>>(emptyList()) }
    var query by remember { mutableStateOf("") }

    suspend fun refresh() {
        chats = repo.listChats(myUid)
    }

    LaunchedEffect(Unit) {
        refresh()
    }

    val filteredChats = chats.filter {
        it.peerNickname.contains(query, ignoreCase = true) ||
            (it.lastMessageText ?: "").contains(query, ignoreCase = true)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D2027))
            .padding(8.dp)
    ) {
        val panelWidth = maxWidth.coerceAtMost(430.dp)
        Column(
            modifier = Modifier
                .width(panelWidth)
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF101014))
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "BereZka",
                    color = WhiteSoft,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "☰", color = WhiteSoft, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(14.dp))

            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Найди свой листик", color = Color(0xFFB4B4B4), fontSize = 20.sp) },
                trailingIcon = { Text("z", color = BerezkaGreen, fontSize = 36.sp, fontWeight = FontWeight.Bold) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF4A4A4E),
                    unfocusedContainerColor = Color(0xFF4A4A4E),
                    focusedTextColor = WhiteSoft,
                    unfocusedTextColor = WhiteSoft,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BerezkaGreen
                ),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (chats.isEmpty()) {
                EmptyChatsState(
                    onCreateDemoChat = {
                        scope.launch {
                            repo.insertChat(
                                ChatEntity(
                                    chatId = "chat_demo_1",
                                    myUid = myUid,
                                    peerUid = "peer_1",
                                    peerNickname = "Серега",
                                    lastMessageText = "Го в кальянку",
                                    lastMessageDate = System.currentTimeMillis(),
                                    unreadCount = 3
                                )
                            )
                            repo.insertChat(
                                ChatEntity(
                                    chatId = "chat_demo_2",
                                    myUid = myUid,
                                    peerUid = "peer_2",
                                    peerNickname = "Лиза",
                                    lastMessageText = "Идем на баскет",
                                    lastMessageDate = System.currentTimeMillis() - 1000L * 60L * 2L,
                                    unreadCount = 5
                                )
                            )
                            refresh()
                        }
                    }
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredChats) { chat ->
                        ChatListItem(
                            chat = chat,
                            onClick = { onChatClick(chat.chatId, chat.peerNickname) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Назад",
                color = Muted,
                modifier = Modifier.clickable(onClick = onBackToWelcome)
            )
        }
    }
}

@Composable
private fun EmptyChatsState(
    onCreateDemoChat: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Пока нет чатов", color = WhiteSoft, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Создайте тестовый чат, чтобы проверить экран диалога",
            color = Muted,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Создать тестовые чаты",
            color = BerezkaGreen,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onCreateDemoChat)
        )
    }
}

@Composable
private fun ChatListItem(
    chat: ChatEntity,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color(0xFF2A2A2E)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.peerNickname.take(1).uppercase(),
                color = WhiteSoft,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(BerezkaGreen)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.peerNickname,
                color = WhiteSoft,
                fontWeight = FontWeight.SemiBold,
                fontSize = 34.sp
            )
            Text(
                text = chat.lastMessageText ?: "Нет сообщений",
                color = Muted,
                fontSize = 27.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = formatChatTime(chat.lastMessageDate),
                color = Color(0xFF68686F),
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (chat.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(BerezkaGreen)
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chat.unreadCount.toString().padStart(2, '0'),
                        color = Color(0xFF111111),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

private fun formatChatTime(lastMessageDate: Long?): String {
    if (lastMessageDate == null) return ""
    val now = Calendar.getInstance()
    val msg = Calendar.getInstance().apply { timeInMillis = lastMessageDate }

    val isSameDay = now.get(Calendar.YEAR) == msg.get(Calendar.YEAR) &&
        now.get(Calendar.DAY_OF_YEAR) == msg.get(Calendar.DAY_OF_YEAR)
    if (isSameDay) {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(lastMessageDate))
    }

    now.add(Calendar.DAY_OF_YEAR, -1)
    val isYesterday = now.get(Calendar.YEAR) == msg.get(Calendar.YEAR) &&
        now.get(Calendar.DAY_OF_YEAR) == msg.get(Calendar.DAY_OF_YEAR)
    if (isYesterday) return "Вчера"

    return SimpleDateFormat("dd.MM", Locale.getDefault()).format(Date(lastMessageDate))
}
