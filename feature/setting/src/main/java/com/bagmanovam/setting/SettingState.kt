package com.bagmanovam.setting

import androidx.compose.runtime.Immutable

@Immutable
data class SettingState(
    val notificationEnabled: Boolean = false,
    val wifiOnly: Boolean = false,
    val theme: Theme = Theme.SYSTEM
)

enum class Theme {
    SYSTEM,
    LIGHT,
    DARK
}