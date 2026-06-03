package com.bagmanovam.news.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.intl.Locale
import com.bagmanovam.common.presentation.utils.DateFormatter
import com.bagmanovam.domain.model.Article
import kotlin.text.format


@Immutable
data class ArticleUi(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: String,
    val url: String,
)

fun Article.toArticleUi(): ArticleUi {
    return ArticleUi(
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        sourceName = this.sourceName,
        publishedAt = this.publishedAt.displayableDate(),
        url = this.url
    )
}


fun Long.displayableDate(): String {
    return DateFormatter.formatDate(
        timeStamp = this,
    )
}