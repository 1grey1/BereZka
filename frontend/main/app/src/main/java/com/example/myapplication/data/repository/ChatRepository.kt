package com.example.myapplication.data.repository

import com.example.myapplication.data.local.room.dao.ChatDao
import com.example.myapplication.data.local.room.entity.ChatEntity

class ChatRepository(
    private val chatDao: ChatDao
) {

    suspend fun insertChat(chat: ChatEntity) {
        chatDao.insertChat(chat)
    }

    suspend fun getChatById(chatId: String): ChatEntity? {
        return chatDao.getChatById(chatId)
    }

    suspend fun getChatByUsers(myUid: String, peerUid: String): ChatEntity? {
        return chatDao.getChatByUsers(myUid, peerUid)
    }

    suspend fun listChats(myUid: String): List<ChatEntity> {
        return chatDao.listChats(myUid)
    }

    suspend fun updateChatPreview(
        chatId: String,
        lastMessageText: String?,
        lastMessageDate: Long?
    ) {
        chatDao.updateChatPreview(chatId, lastMessageText, lastMessageDate)
    }

    suspend fun incrementUnread(chatId: String) {
        chatDao.incrementUnread(chatId)
    }

    suspend fun markChatAsRead(chatId: String) {
        chatDao.markChatAsRead(chatId)
    }

    suspend fun deleteChat(chatId: String) {
        chatDao.deleteChat(chatId)
    }
}
