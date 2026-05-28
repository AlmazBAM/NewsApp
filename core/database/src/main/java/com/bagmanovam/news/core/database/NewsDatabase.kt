package com.bagmanovam.news.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bagmanovam.news.core.database.dto.ArticleDbDto
import com.bagmanovam.news.core.database.dto.SubscriptionDbDto

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