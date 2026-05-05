//package com.example.myapplication.ui.screens
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.statusBarsPadding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.Search
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.MoreVert
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.myapplication.ui.components.AuthGradientPage
//import com.example.myapplication.ui.theme.BerezkaGreen
//import com.example.myapplication.ui.theme.BerezkaGreenSoft
//import com.example.myapplication.ui.theme.Muted
//import com.example.myapplication.ui.theme.WhiteSoft
//
//data class ChatUiModel(
//    val id: Int,
//    val name: String,
//    val message: String,
//    val time: String,
//    val unreadCount: Int = 0,
//    val isOnline: Boolean = false,
//    val avatarLetter: String
//)
//
//@Composable
//fun ChatsScreen(
//    onChatClick: (Int) -> Unit = {},
//    onProfileClick: () -> Unit = {},
//    onSearchClick: () -> Unit = {},
//    onNewChatClick: () -> Unit = {}
//) {
//    val chats = remember {
//        listOf(
//            ChatUiModel(
//                id = 1,
//                name = "Алина",
//                message = "Ты сегодня зайдёшь в береZку вечером?",
//                time = "21:14",
//                unreadCount = 2,
//                isOnline = true,
//                avatarLetter = "А"
//            ),
//            ChatUiModel(
//                id = 2,
//                name = "Дима",
//                message = "Я скинул тебе фотки и новый плейлист.",
//                time = "20:48",
//                unreadCount = 0,
//                isOnline = true,
//                avatarLetter = "Д"
//            ),
//            ChatUiModel(
//                id = 3,
//                name = "Семья",
//                message = "Мама: Не забудь позвонить бабушке 🌿",
//                time = "19:22",
//                unreadCount = 5,
//                isOnline = false,
//                avatarLetter = "С"
//            ),
//            ChatUiModel(
//                id = 4,
//                name = "Игорь",
//                message = "Окей, тогда завтра после обеда",
//                time = "18:05",
//                unreadCount = 0,
//                isOnline = false,
//                avatarLetter = "И"
//            ),
//            ChatUiModel(
//                id = 5,
//                name = "Марина",
//                message = "Мне очень нравится, как выглядит приложение 😍",
//                time = "17:41",
//                unreadCount = 1,
//                isOnline = true,
//                avatarLetter = "М"
//            ),
//            ChatUiModel(
//                id = 6,
//                name = "Рабочий чат",
//                message = "Новая задача уже в трекере",
//                time = "16:12",
//                unreadCount = 3,
//                isOnline = false,
//                avatarLetter = "Р"
//            )
//        )
//    }
//
//    AuthGradientPage {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .statusBarsPadding()
//                .padding(horizontal = 20.dp, vertical = 18.dp)
//        ) {
//            ChatsHeader(
//                onProfileClick = onProfileClick,
//                onSearchClick = onSearchClick,
//                onNewChatClick = onNewChatClick
//            )
//
//            Spacer(modifier = Modifier.height(18.dp))
//
//            ChatsTopCard()
//
//            Spacer(modifier = Modifier.height(18.dp))
//
//            Text(
//                text = "Диалоги",
//                color = WhiteSoft,
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            LazyColumn(
//                verticalArrangement = Arrangement.spacedBy(12.dp),
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(chats) { chat ->
//                    ChatItem(
//                        chat = chat,
//                        onClick = { onChatClick(chat.id) }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun ChatsHeader(
//    onProfileClick: () -> Unit,
//    onSearchClick: () -> Unit,
//    onNewChatClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            modifier = Modifier
//                .weight(1f)
//                .clickable(onClick = onProfileClick),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(52.dp)
//                    .clip(CircleShape)
//                    .background(
//                        Brush.linearGradient(
//                            colors = listOf(BerezkaGreenSoft, BerezkaGreen)
//                        )
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Б",
//                    color = Color(0xFF0B1810),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Column {
//                Text(
//                    text = "береZка",
//                    color = WhiteSoft,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp
//                )
//                Text(
//                    text = "Твои тёплые диалоги",
//                    color = Muted,
//                    fontSize = 13.sp
//                )
//            }
//        }
//
//        HeaderIconButton(
//            icon = Icons.Outlined.Search,
//            onClick = onSearchClick
//        )
//
//        Spacer(modifier = Modifier.width(10.dp))
//
//        HeaderIconButton(
//            icon = Icons.Outlined.Edit,
//            onClick = onNewChatClick
//        )
//    }
//}
//
//@Composable
//private fun HeaderIconButton(
//    icon: androidx.compose.ui.graphics.vector.ImageVector,
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .size(46.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(Color.White.copy(alpha = 0.08f))
//            .border(
//                width = 1.dp,
//                color = Color.White.copy(alpha = 0.10f),
//                shape = RoundedCornerShape(16.dp)
//            )
//            .clickable(onClick = onClick),
//        contentAlignment = Alignment.Center
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = null,
//            tint = WhiteSoft
//        )
//    }
//}
//
//@Composable
//private fun ChatsTopCard() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(26.dp))
//            .background(
//                Brush.verticalGradient(
//                    colors = listOf(
//                        Color.White.copy(alpha = 0.13f),
//                        Color.White.copy(alpha = 0.08f)
//                    )
//                )
//            )
//            .border(
//                width = 1.dp,
//                color = Color.White.copy(alpha = 0.10f),
//                shape = RoundedCornerShape(26.dp)
//            )
//            .padding(18.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .size(54.dp)
//                .clip(RoundedCornerShape(18.dp))
//                .background(
//                    Brush.linearGradient(
//                        colors = listOf(BerezkaGreenSoft, BerezkaGreen)
//                    )
//                ),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                imageVector = Icons.Outlined.MoreVert,
//                contentDescription = null,
//                tint = Color(0xFF0B1810)
//            )
//        }
//
//        Spacer(modifier = Modifier.width(14.dp))
//
//        Column(modifier = Modifier.weight(1f)) {
//            Text(
//                text = "Здесь живут твои чаты",
//                color = WhiteSoft,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = "Открывай диалоги, отвечай друзьям и оставайся на связи.",
//                color = Muted,
//                fontSize = 13.sp,
//                lineHeight = 18.sp
//            )
//        }
//
//        TextButton(onClick = {}) {
//            Text(
//                text = "Все",
//                color = BerezkaGreenSoft,
//                fontWeight = FontWeight.SemiBold
//            )
//        }
//    }
//}
//
//@Composable
//private fun ChatItem(
//    chat: ChatUiModel,
//    onClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(24.dp))
//            .background(Color.White.copy(alpha = 0.07f))
//            .border(
//                width = 1.dp,
//                color = Color.White.copy(alpha = 0.10f),
//                shape = RoundedCornerShape(24.dp)
//            )
//            .clickable(onClick = onClick)
//            .padding(horizontal = 16.dp, vertical = 14.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box {
//            Box(
//                modifier = Modifier
//                    .size(56.dp)
//                    .clip(CircleShape)
//                    .background(
//                        Brush.linearGradient(
//                            colors = listOf(
//                                Color(0xFF7EF3F0),
//                                BerezkaGreen
//                            )
//                        )
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = chat.avatarLetter,
//                    color = Color(0xFF0B1810),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 22.sp
//                )
//            }
//
//            if (chat.isOnline) {
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .size(14.dp)
//                        .clip(CircleShape)
//                        .background(BerezkaGreenSoft)
//                        .border(2.dp, Color(0xFF13202B), CircleShape)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.width(14.dp))
//
//        Column(
//            modifier = Modifier.weight(1f)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = chat.name,
//                    color = WhiteSoft,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.weight(1f),
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//
//                Text(
//                    text = chat.time,
//                    color = Muted,
//                    fontSize = 12.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Text(
//                text = chat.message,
//                color = Muted,
//                fontSize = 14.sp,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                lineHeight = 18.sp
//            )
//        }
//
//        if (chat.unreadCount > 0) {
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Box(
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .background(BerezkaGreenSoft)
//                    .padding(horizontal = 10.dp, vertical = 6.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = chat.unreadCount.toString(),
//                    color = Color(0xFF0B1810),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 12.sp
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun ChatsScreenPreview() {
//    MaterialTheme {
//        Surface {
//            ChatsScreen()
//        }
//    }
//}
