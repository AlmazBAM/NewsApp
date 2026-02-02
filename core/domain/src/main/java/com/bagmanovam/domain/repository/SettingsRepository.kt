package com.bagmanovam.domain.repository

import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.Settings
import com.bagmanovam.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getSettings(): Flow<Settings>

    suspend fun updateLanguage(language: Language)
    suspend fun updateUpdateInterval(minutes: Int)
    suspend fun updateTheme(theme: Theme)

    suspend fun updateNotificationsEnabled(enabled: Boolean )
    suspend fun updateWifiOnly(wifiOnly: Boolean)
}