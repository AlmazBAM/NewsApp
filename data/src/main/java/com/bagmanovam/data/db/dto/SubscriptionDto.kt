package com.bagmanovam.data.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class SubscriptionDto(
    @PrimaryKey
    val topic: String
)