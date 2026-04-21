package com.example.myapplication.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.room.entity.ChatEntity

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: ChatEntity)

    @Query("SELECT * FROM chats WHERE chat_id = :chatId LIMIT 1")
    suspend fun getChatById(chatId: String): ChatEntity?

    @Query("SELECT * FROM chats WHERE my_uid = :myUid AND peer_uid = :peerUid LIMIT 1")
    suspend fun getChatByUsers(myUid: String, peerUid: String): ChatEntity?

    @Query("SELECT * FROM chats WHERE my_uid = :myUid ORDER BY last_message_date DESC")
    suspend fun listChats(myUid: String): List<ChatEntity>

    @Query(
        "UPDATE chats " +
            "SET last_message_text = :lastMessageText, last_message_date = :lastMessageDate " +
            "WHERE chat_id = :chatId"
    )
    suspend fun updateChatPreview(
        chatId: String,
        lastMessageText: String?,
        lastMessageDate: Long?
    )

    @Query("UPDATE chats SET unread_count = unread_count + 1 WHERE chat_id = :chatId")
    suspend fun incrementUnread(chatId: String)

    @Query("UPDATE chats SET unread_count = 0 WHERE chat_id = :chatId")
    suspend fun markChatAsRead(chatId: String)

    @Query("DELETE FROM chats WHERE chat_id = :chatId")
    suspend fun deleteChat(chatId: String)
}
