package com.bagmanovam.network

import com.bagmanovam.common.domain.NetworkError
import com.bagmanovam.network.dto.NewsResponseDto
import com.bagmanovam.network.util.KtorUrlBuilder
import com.bagmanovam.network.util.NewsEverythingEndpoint
import com.bagmanovam.network.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class NewsApi(
    private val httpClient: HttpClient,
    private val urlBuilder: KtorUrlBuilder
) {

    suspend fun loadArticles(topic: String, language: String): com.bagmanovam.common.domain.Result<NewsResponseDto, NetworkError> {
        return safeCall {
            val url = urlBuilder.build(NewsEverythingEndpoint(topic, language))
            httpClient.get(urlString = url)
        }
    }
}