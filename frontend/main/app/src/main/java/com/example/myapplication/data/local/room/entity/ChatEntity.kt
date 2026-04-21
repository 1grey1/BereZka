package com.example.myapplication.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "chats",
    indices = [
        Index(value = ["peer_uid"], name = "idx_chats_peer_uid"),
        Index(value = ["peer_nickname"], name = "idx_chats_peer_nickname"),
        Index(value = ["my_uid", "peer_uid"], unique = true, name = "idx_unique_private_chat")
    ]
)
data class ChatEntity(
    @PrimaryKey
    @ColumnInfo(name = "chat_id")
    val chatId: String,
    @ColumnInfo(name = "my_uid")
    val myUid: String,
    @ColumnInfo(name = "peer_uid")
    val peerUid: String,
    @ColumnInfo(name = "peer_nickname")
    val peerNickname: String,
    @ColumnInfo(name = "peer_avatar")
    val peerAvatar: String? = null,
    @ColumnInfo(name = "last_message_text")
    val lastMessageText: String? = null,
    @ColumnInfo(name = "last_message_date")
    val lastMessageDate: Long? = null,
    @ColumnInfo(name = "unread_count")
    val unreadCount: Int = 0
)
