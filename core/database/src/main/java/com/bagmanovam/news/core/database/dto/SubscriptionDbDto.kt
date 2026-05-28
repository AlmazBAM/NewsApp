package com.bagmanovam.news.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class SubscriptionDbDto(
    @PrimaryKey
    val topic: String
)