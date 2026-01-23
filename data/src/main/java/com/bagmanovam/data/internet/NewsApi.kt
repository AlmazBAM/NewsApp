package com.bagmanovam.data.internet

import com.bagmanovam.common.domain.NetworkError
import com.bagmanovam.common.domain.Result
import com.bagmanovam.data.internet.dto.NewsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsApi(
    private val httpClient: HttpClient
) {

    suspend fun loadArticles(topic: String): Result<NewsResponseDto, NetworkError> {
        val response = httpClient.get(urlString = "") {
            parameter("q", topic)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val newsResponse = response.body<NewsResponseDto>()
                Result.Success(newsResponse)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}