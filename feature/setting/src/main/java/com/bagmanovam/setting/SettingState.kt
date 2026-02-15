package com.bagmanovam.setting

import androidx.compose.runtime.Immutable
import com.bagmanovam.domain.model.Theme

@Immutable
data class SettingState(
    val notificationEnabled: Boolean = false,
    val wifiOnly: Boolean = false,
    val theme: Theme = Theme.SYSTEM,    val name: String = "",
    val email: String = "",
    val icon: String? = null
)
