package com.bagmanovam.data.internet.dto

import com.bagmanovam.domain.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    @SerialName("articles")
    val articles: List<Article> = listOf()
)
