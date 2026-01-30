package com.bagmanovam.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: Long,
    val url: String
)
