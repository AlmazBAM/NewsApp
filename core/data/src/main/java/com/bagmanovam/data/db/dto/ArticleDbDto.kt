package com.bagmanovam.data.db.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    tableName = "articles",
    primaryKeys = ["url", "topic"],
    foreignKeys = [
        ForeignKey(
            entity = SubscriptionDbDto::class,
            parentColumns = ["topic"],
            childColumns = ["topic"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(value = ["topic"])
    ]
)
data class ArticleDbDto(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: Long,
    val url: String,
    val topic: String,
)
