@file:OptIn(ExperimentalTime::class)

package com.bagmanovam.data.mapper

import android.os.Build
import com.bagmanovam.data.db.dto.ArticleDbDto
import com.bagmanovam.data.internet.dto.ArticleDto
import com.bagmanovam.domain.model.Article
import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.RefreshConfig
import com.bagmanovam.domain.model.Settings
import com.bagmanovam.domain.model.UpdateInterval
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.ExperimentalTime

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

fun Settings.toRefreshConfig(): RefreshConfig {
    return RefreshConfig(
        language = language,
        updateInterval = updateInterval,
        theme = theme,
        wifiOnly = wifiOnly
    )
}

fun Language.toQueryParam(): String {
    return when (this) {
        Language.ENGLISH -> "en"
        Language.RUSSIAN -> "ru"
        Language.FRENCH -> "fr"
        Language.GERMAN -> "de"
    }
}


fun Int.toUpdateInterval(): UpdateInterval {
    return UpdateInterval.entries.firstOrNull { it.minutes == this } ?: Settings.DEFAULT_UPDATE_INTERVAL
}

fun String.toTimeStamp(locale: Locale = Locale.getDefault()): Long {
    return runCatching {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)
            LocalDateTime.parse(this, formatter).toInstant(ZoneOffset.UTC).toEpochMilli()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }.getOrElse {
        System.currentTimeMillis()
    }

}