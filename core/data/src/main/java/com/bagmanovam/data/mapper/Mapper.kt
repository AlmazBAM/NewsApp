@file:OptIn(ExperimentalTime::class)

package com.bagmanovam.data.mapper

import com.bagmanovam.data.db.dto.ArticleDbDto
import com.bagmanovam.data.internet.dto.ArticleDto
import com.bagmanovam.domain.model.Article
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun List<ArticleDbDto>.toArticle(): List<Article> {
    return map {
        Article(
            title = it.title,
            description = it.description,
            imageUrl = it.imageUrl,
            sourceName = it.sourceName,
            publishedAt = it.publishedAt,
            url = it.url
        )
    }.distinct()
}

fun List<ArticleDto>.toArticleDb(topic: String): List<ArticleDbDto> {
    return map {
        ArticleDbDto(
            title = it.title,
            description = it.description,
            imageUrl = it.urlToImage,
            sourceName = it.source.name,
            publishedAt = it.publishedAt.toTimeStamp(Locale.getDefault()),
            url = it.url,
            topic = topic
        )
    }.distinct()
}



fun String.toTimeStamp(): Long {
    return runCatching {
        val instant = Instant.parse(this)
        val millis = instant.toEpochMilliseconds()
        millis
    }.getOrElse { System.currentTimeMillis() }
}

fun String.toTimeStamp(locale: Locale = Locale.getDefault()): Long {
    return runCatching {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)
        LocalDateTime.parse(this, formatter).toInstant(ZoneOffset.UTC).toEpochMilli()
    }.getOrElse {
        System.currentTimeMillis()
    }

}