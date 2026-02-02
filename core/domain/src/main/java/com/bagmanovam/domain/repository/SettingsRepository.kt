package com.bagmanovam.domain.repository

import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.Settings
import com.bagmanovam.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getSettings(): Flow<Settings>

    fun updateLanguage(language: Language)
    fun updateUpdateInterval(minutes: Int)
    fun updateTheme(theme: Theme)

    fun updateNotificationsEnabled(enabled: Boolean )
    fun updateWifiOnly(wifiOnly: Boolean)
}