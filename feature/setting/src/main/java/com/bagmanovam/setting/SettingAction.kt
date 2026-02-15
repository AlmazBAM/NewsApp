package com.bagmanovam.setting

import android.net.Uri
import com.bagmanovam.domain.model.Theme

sealed interface SettingAction {
    data class ToggleNotification(val enabled: Boolean) : SettingAction
    data class ToggleWifiOnly(val enabled: Boolean) : SettingAction
    data class UpdateTheme(val theme: Theme) : SettingAction
    data class UpdateAvatar(val imageUri: Uri) : SettingAction
}