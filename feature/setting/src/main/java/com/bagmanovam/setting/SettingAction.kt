package com.bagmanovam.setting

sealed interface SettingAction {
    data class ToggleNotification(val enabled: Boolean) : SettingAction
    data class ToggleWifiOnly(val enabled: Boolean) : SettingAction
}