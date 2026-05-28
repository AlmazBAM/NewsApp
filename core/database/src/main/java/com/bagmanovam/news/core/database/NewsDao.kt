package com.bagmanovam.news.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Transaction
import com.bagmanovam.news.core.database.dto.ArticleDbDto
import com.bagmanovam.news.core.database.dto.SubscriptionDbDto
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM subscriptions")
    fun getAllSubscriptions(): Flow<List<SubscriptionDbDto>>

    @Insert(entity = SubscriptionDbDto::class, onConflict = IGNORE)
    fun addSubscription(subscriptionDto: SubscriptionDbDto)

    @Transaction
    @Delete
    fun deleteSubscription(subscriptionDto: SubscriptionDbDto): Int

    @Query("SELECT * FROM articles WHERE topic IN (:topics) ORDER BY publishedAt DESC")
    fun getArticleByTopics(topics: List<String>): Flow<List<ArticleDbDto>>

    @Insert(entity = ArticleDbDto::class, onConflict = IGNORE)
    fun addArticles(articles: List<ArticleDbDto>): List<Long>

    @Query("DELETE FROM articles WHERE topic IN (:topics)")
    fun deleteAllArticlesByTopics(topics: List<String>): Int
}