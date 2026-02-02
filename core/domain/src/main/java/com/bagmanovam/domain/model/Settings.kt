package com.bagmanovam.domain.model

data class Settings(
    val language: Language,
    val updateInterval: UpdateInterval,
    val theme: Theme,
    val showNotification: Boolean,
    val wifiOnly: Boolean
)

enum class Theme {
    SYSTEM, LIGHT, DARK
}

enum class Language {
    ENGLISH, RUSSIAN, FRENCH, GERMAN
}

enum class UpdateInterval(val minutes: Int) {
    MIN_15(15),
    MIN_30(30),
    HOUR_1(60),
    HOUR_2(120),
    HOUR_4(240),
    HOUR_8(480),
    HOUR_24(1440),
}
