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
import com.bagmanovam.domain.model.Article
import com.bagmanovam.domain.repository.NewsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.collections.emptyList
import kotlin.time.measureTime

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

    override suspend fun updateArticlesForTopic(topic: String) {
        try {
            var articles = listOf<ArticleDbDto>()
            newsApi.loadArticles(topic = topic)
                .onSuccess {
                    articles = it.articles.toArticleDb(topic = topic)
                }.onError {
                    articles = emptyList()
                }
            newsDao.addArticles(articles)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e(TAG, "updateArticlesForTopic: error ${e.message}")
        }
    }

    override suspend fun removeSubscription(topic: String) {
        newsDao.deleteSubscription(SubscriptionDbDto(topic = topic))
    }

    override suspend fun updateArticlesForAllSubscriptions(): List<String> {
        val subscriptions = newsDao.getAllSubscriptions().first()
        coroutineScope {
            val time1 = measureTime {
                subscriptions.forEach {
                    launch {
                        newsApi.loadArticles(it.topic)
                    }
                }
            }

            val time2 = measureTime {
                subscriptions.forEach {
                    newsApi.loadArticles(it.topic)
                }
            }
            Log.d(TAG, "updateArticlesForAllSubscriptions: coroutine $time1, $time2")
        }

        return emptyList()

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