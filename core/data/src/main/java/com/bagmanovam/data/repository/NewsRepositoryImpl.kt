package com.bagmanovam.data.repository

import android.util.Log
import com.bagmanovam.common.domain.onError
import com.bagmanovam.common.domain.onSuccess
import com.bagmanovam.data.db.NewsDao
import com.bagmanovam.data.db.dto.ArticleDbDto
import com.bagmanovam.data.db.dto.SubscriptionDbDto
import com.bagmanovam.data.internet.NewsApi
import com.bagmanovam.data.mapper.toArticle
import com.bagmanovam.data.mapper.toArticleDb
import com.bagmanovam.data.mapper.toQueryParam
import com.bagmanovam.domain.model.Article
import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.repository.NewsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class NewsRepositoryImpl(
    private val newsDao: NewsDao,
    private val newsApi: NewsApi,
) : NewsRepository {

    override fun getAllSubscriptions(): Flow<List<String>> {
        return newsDao.getAllSubscriptions().map { subscriptions ->
            subscriptions.map { dto ->
                dto.topic
            }
        }
    }

    override suspend fun addSubscription(topic: String) {
        newsDao.addSubscription(SubscriptionDbDto(topic = topic))
    }

    override suspend fun updateArticlesForTopic(topic: String, language: Language): Boolean {
        try {
            var articles = listOf<ArticleDbDto>()
            newsApi.loadArticles(
                topic = topic,
                language = language.toQueryParam()
            )
                .onSuccess {
                    articles = it.articles.toArticleDb(topic = topic)
                }.onError {
                    articles = emptyList()
                }
            val ids = newsDao.addArticles(articles)
            return ids.any { it != -1L }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e(TAG, "updateArticlesForTopic: error ${e.message}")
            return false
        }
    }

    override suspend fun removeSubscription(topic: String) {
        newsDao.deleteSubscription(SubscriptionDbDto(topic = topic))
    }

    override suspend fun updateArticlesForAllSubscriptions(language: Language): List<String> {
        val updatedTopics = mutableListOf<String>()
        val subscriptions = newsDao.getAllSubscriptions().first()
        coroutineScope {
            subscriptions.forEach { subscriptionDbDto ->
                launch {
                    updateArticlesForTopic(subscriptionDbDto.topic, language).also {
                        if (it) updatedTopics.add(subscriptionDbDto.topic)
                    }
                }
            }
        }

        return updatedTopics

    }

    override fun getArticlesByTopics(topics: List<String>): Flow<List<Article>> {
        return newsDao.getArticleByTopics(topics = topics).map { articles ->
            articles.toArticle()
        }
    }

    override suspend fun clearAllArticles(topics: List<String>) {
        newsDao.deleteAllArticlesByTopics(topics = topics)
    }

    companion object {
        private val TAG = NewsRepositoryImpl::class.java.simpleName
    }
}