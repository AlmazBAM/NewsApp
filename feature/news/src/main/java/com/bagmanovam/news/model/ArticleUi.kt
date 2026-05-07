package com.bagmanovam.news.model

import androidx.compose.runtime.Immutable
import com.bagmanovam.domain.model.Article


@Immutable
data class ArticleUi(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: Long,
    val url: String
)

fun Article.toArticleUi(): ArticleUi {
    return ArticleUi(
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        sourceName = this.sourceName,
        publishedAt = this.publishedAt,
        url = this.url
    )
}