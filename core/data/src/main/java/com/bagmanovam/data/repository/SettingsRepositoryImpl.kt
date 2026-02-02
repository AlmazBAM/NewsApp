package com.bagmanovam.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bagmanovam.data.mapper.toUpdateInterval
import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.Settings
import com.bagmanovam.domain.model.Theme
import com.bagmanovam.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class SettingsRepositoryImpl(
    private val context: Context,
) : SettingsRepository {

    private val languageKey = stringPreferencesKey("language")
    private val updateIntervalKey = intPreferencesKey("interval")
    private val themeKey = stringPreferencesKey("theme")
    private val notificationsEnabledKey = booleanPreferencesKey("notifications_enabled")
    private val wifiOnlyKey = booleanPreferencesKey("wifi_only")
    override fun getSettings(): Flow<Settings> {
        return context.dataStore.data.map { prefs ->
            Settings(
                language = Language.valueOf(prefs[languageKey] ?: Settings.DEFAULT_LANGUAGE.name),
                updateInterval = prefs[updateIntervalKey]?.toUpdateInterval() ?: Settings.DEFAULT_UPDATE_INTERVAL,
                theme = Theme.valueOf(prefs[themeKey] ?: Theme.SYSTEM.name),
                showNotification = prefs[notificationsEnabledKey] ?: Settings.DEFAULT_NOTIFICATIONS_ENABLED,
                wifiOnly = prefs[wifiOnlyKey] ?: Settings.DEFAULT_WIFI_ONLY
            )
        }
    }

    override suspend fun updateLanguage(language: Language) {
        context.dataStore.edit {
            it[languageKey] = language.name
        }
    }

    override suspend fun updateUpdateInterval(minutes: Int) {
        context.dataStore.edit {
            it[updateIntervalKey] = minutes
        }
    }

    override suspend fun updateTheme(theme: Theme) {
        context.dataStore.edit {
            it[themeKey] = theme.name
        }
    }

    override suspend fun updateNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit {
            it[notificationsEnabledKey] = enabled
        }
    }

    override suspend fun updateWifiOnly(wifiOnly: Boolean) {
        context.dataStore.edit {
            it[wifiOnlyKey] = wifiOnly
        }
    }
}