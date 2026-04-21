package com.example.myapplication.data.repository

import com.example.myapplication.data.local.room.dao.MessageDao
import com.example.myapplication.data.local.room.entity.MessageEntity

class MessageRepository(
    private val messageDao: MessageDao
) {

    suspend fun insertMessage(message: MessageEntity) {
        messageDao.insertMessage(message)
    }

    suspend fun listMessagesByChat(chatId: String): List<MessageEntity> {
        return messageDao.listMessagesByChat(chatId)
    }

    suspend fun deleteMessagesByChat(chatId: String) {
        messageDao.deleteMessagesByChat(chatId)
    }
}
