package com.bagmanovam.data.internet

import com.bagmanovam.common.domain.NetworkError
import com.bagmanovam.common.domain.Result
import com.bagmanovam.data.internet.dto.NewsResponseDto
import com.bagmanovam.data.internet.util.KtorUrlBuilder
import com.bagmanovam.data.internet.util.NewsEverythingEndpoint
import com.bagmanovam.data.internet.util.safeCall
import com.bagmanovam.domain.model.Language
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class NewsApi(
    private val httpClient: HttpClient,
    private val urlBuilder: KtorUrlBuilder
) {

    suspend fun loadArticles(topic: String, language: String): Result<NewsResponseDto, NetworkError> {
        return safeCall {
            val url = urlBuilder.build(NewsEverythingEndpoint(topic, language))
            httpClient.get(urlString = url)
        }
    }
}