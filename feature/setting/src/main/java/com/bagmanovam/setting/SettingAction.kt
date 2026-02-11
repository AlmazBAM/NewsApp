package com.bagmanovam.setting

import com.bagmanovam.domain.model.Theme

sealed interface SettingAction {
    data class ToggleNotification(val enabled: Boolean) : SettingAction
    data class ToggleWifiOnly(val enabled: Boolean) : SettingAction
    data class UpdateTheme(val theme: Theme) : SettingAction
}