package com.bagmanovam.domain.model

data class RefreshConfig(
    val language: Language,
    val updateInterval: UpdateInterval,
    val theme: Theme,
    val wifiOnly: Boolean,
)
