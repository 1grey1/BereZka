package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.local.room.MessengerRoomDatabase
import com.example.myapplication.data.local.room.entity.ChatEntity
import com.example.myapplication.data.local.room.entity.MessageEntity
import com.example.myapplication.data.repository.ChatRepository
import com.example.myapplication.data.repository.MessageRepository
import com.example.myapplication.ui.components.AuthGradientPage
import com.example.myapplication.ui.components.GlassCard
import com.example.myapplication.ui.theme.Muted
import com.example.myapplication.ui.theme.WhiteSoft
import kotlinx.coroutines.launch

@Composable
fun DebugDbScreen(
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val database = remember { MessengerRoomDatabase.getInstance(context) }
    val chatRepository = remember { ChatRepository(database.chatDao()) }
    val messageRepository = remember { MessageRepository(database.messageDao()) }

    val myUid = "debug_me"
    val peerUid = "debug_peer"
    val chatId = "debug_chat_1"

    var output by remember { mutableStateOf("Press actions below to test local Room storage.") }

    fun runDbAction(actionName: String, block: suspend () -> String) {
        scope.launch {
            output = try {
                block()
            } catch (e: Exception) {
                "$actionName: ERROR -> ${e.message}"
            }
        }
    }

    AuthGradientPage {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }

            GlassCard {
                Text(
                    text = "Debug DB",
                    color = WhiteSoft,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Temporary screen to validate chat/message local storage.",
                    color = Muted,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        runDbAction("insertChat") {
                            val chat = ChatEntity(
                                chatId = chatId,
                                myUid = myUid,
                                peerUid = peerUid,
                                peerNickname = "Debug Peer",
                                peerAvatar = null
                            )
                            chatRepository.insertChat(chat)
                            "insertChat: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("1) insertChat") }

                Button(
                    onClick = {
                        runDbAction("getChatById") {
                            val chat = chatRepository.getChatById(chatId)
                            "getChatById: ${chat?.toString() ?: "null"}"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("2) getChatById") }

                Button(
                    onClick = {
                        runDbAction("getChatByUsers") {
                            val chat = chatRepository.getChatByUsers(myUid, peerUid)
                            "getChatByUsers: ${chat?.toString() ?: "null"}"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("3) getChatByUsers") }

                Button(
                    onClick = {
                        runDbAction("listChats") {
                            val chats = chatRepository.listChats(myUid)
                            "listChats: count=${chats.size}\n$chats"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("4) listChats") }

                Button(
                    onClick = {
                        runDbAction("insertMessage") {
                            val now = System.currentTimeMillis()
                            val message = MessageEntity(
                                messageId = "msg_$now",
                                chatId = chatId,
                                fromUid = myUid,
                                toUid = peerUid,
                                text = "Debug message at $now",
                                createdAt = now
                            )
                            messageRepository.insertMessage(message)
                            chatRepository.updateChatPreview(
                                chatId = chatId,
                                lastMessageText = message.text,
                                lastMessageDate = now
                            )
                            chatRepository.incrementUnread(chatId)
                            "insertMessage + updateChatPreview + incrementUnread: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("5) insertMessage (+preview, +unread)") }

                Button(
                    onClick = {
                        runDbAction("listMessagesByChat") {
                            val messages = messageRepository.listMessagesByChat(chatId)
                            "listMessagesByChat: count=${messages.size}\n$messages"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("6) listMessagesByChat") }

                Button(
                    onClick = {
                        runDbAction("updateChatPreview") {
                            val now = System.currentTimeMillis()
                            chatRepository.updateChatPreview(
                                chatId = chatId,
                                lastMessageText = "Manual preview update",
                                lastMessageDate = now
                            )
                            "updateChatPreview: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("7) updateChatPreview") }

                Button(
                    onClick = {
                        runDbAction("incrementUnread") {
                            chatRepository.incrementUnread(chatId)
                            "incrementUnread: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("8) incrementUnread") }

                Button(
                    onClick = {
                        runDbAction("markChatAsRead") {
                            chatRepository.markChatAsRead(chatId)
                            "markChatAsRead: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("9) markChatAsRead") }

                Button(
                    onClick = {
                        runDbAction("deleteMessagesByChat") {
                            messageRepository.deleteMessagesByChat(chatId)
                            "deleteMessagesByChat: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("10) deleteMessagesByChat") }

                Button(
                    onClick = {
                        runDbAction("deleteChat") {
                            chatRepository.deleteChat(chatId)
                            "deleteChat: OK"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("11) deleteChat") }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = output,
                    color = WhiteSoft,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp
                )
            }
        }
    }
}

