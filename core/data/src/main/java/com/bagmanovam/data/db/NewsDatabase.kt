package com.bagmanovam.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bagmanovam.data.db.dto.ArticleDbDto
import com.bagmanovam.data.db.dto.SubscriptionDbDto

@Database(
    entities = [
        ArticleDbDto::class,
        SubscriptionDbDto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun dao(): NewsDao
}