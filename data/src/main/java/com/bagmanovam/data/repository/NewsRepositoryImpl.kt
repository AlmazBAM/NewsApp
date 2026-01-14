package com.bagmanovam.data.repository

import com.bagmanovam.domain.model.Article
import com.bagmanovam.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl : NewsRepository {

    override fun getAllSubscriptions(): Flow<List<String>> {
         TODO("Not yet implemented")
    }

    override suspend fun addSubscription(topic: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateArticlesForTopic(topic: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun removeSubscription(topic: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateArticlesForAllSubscriptions(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getArticlesByTopics(topics: List<String>): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllArticles(topics: List<String>) {
        TODO("Not yet implemented")
    }
}