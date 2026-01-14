package com.bagmanovam.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bagmanovam.data.db.dto.ArticleDto
import com.bagmanovam.data.db.dto.SubscriptionDto

@Database(
    entities = [
        ArticleDto::class,
        SubscriptionDto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun dao(): NewsDao
}