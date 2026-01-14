package com.bagmanovam.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Transaction
import com.bagmanovam.data.db.dto.ArticleDto
import com.bagmanovam.data.db.dto.SubscriptionDto
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM subscriptions")
    fun getAllSubscriptions(): Flow<List<SubscriptionDto>>

    @Insert(entity = SubscriptionDto::class, onConflict = IGNORE)
    fun addSubscription(subscriptionDto: SubscriptionDto)

    @Transaction
    @Delete
    fun deleteSubscription(subscriptionDto: SubscriptionDto): Int

    @Query("SELECT * FROM articles WHERE topic IN (:topics) ORDER BY publishedAt DESC")
    fun getArticleByTopics(topics: List<String>): Flow<List<SubscriptionDto>>

    @Insert(entity = ArticleDto::class, onConflict = IGNORE)
    fun addArticles(articles: List<ArticleDto>)

    @Query("DELETE FROM articles WHERE topic IN (:topics)")
    fun deleteAllArticlesByTopics(topics: List<String>): Int
}