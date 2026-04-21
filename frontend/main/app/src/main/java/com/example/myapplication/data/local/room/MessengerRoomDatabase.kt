package com.example.myapplication.data.local.room

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.room.dao.ChatDao
import com.example.myapplication.data.local.room.dao.MessageDao
import com.example.myapplication.data.local.room.entity.ChatEntity
import com.example.myapplication.data.local.room.entity.MessageEntity

@Database(
    entities = [ChatEntity::class, MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MessengerRoomDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao

    companion object {
        private const val DATABASE_NAME = "berezka_messenger.db"

        @Volatile
        private var INSTANCE: MessengerRoomDatabase? = null

        fun getInstance(context: Context): MessengerRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessengerRoomDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(
                        object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                db.execSQL(
                                    "CREATE INDEX IF NOT EXISTS idx_chats_last_message_date " +
                                        "ON chats(last_message_date DESC)"
                                )
                            }
                        }
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
