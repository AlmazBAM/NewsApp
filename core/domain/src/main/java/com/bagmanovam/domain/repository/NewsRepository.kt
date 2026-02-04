package com.bagmanovam.domain.repository

import com.bagmanovam.domain.model.Article
import com.bagmanovam.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getAllSubscriptions(): Flow<List<String>>
    suspend fun addSubscription(topic: String)
    suspend fun updateArticlesForTopic(topic: String, language: Language): Boolean
    suspend fun removeSubscription(topic: String)
    suspend fun updateArticlesForAllSubscriptions(language: Language): List<String>
    fun getArticlesByTopics(topics: List<String>): Flow<List<Article>>
    suspend fun clearAllArticles(topics: List<String>)
}