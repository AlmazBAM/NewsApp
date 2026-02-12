package com.bagmanovam.setting.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.ui.graphics.vector.ImageVector
import com.bagmanovam.setting.R


enum class ToggleItem(val icon: ImageVector) {
    NOTIFICATION_ENABLED(Icons.Default.Notifications),
    WIFI_ONLY(Icons.Default.Wifi),

    THEME(Icons.Filled.Newspaper),
    PERSONAL_INFORMATION(Icons.Default.PersonOutline),
}

fun ToggleItem.toDisplayableString(context: Context): String {
    return when (this) {
        ToggleItem.NOTIFICATION_ENABLED -> context.getString(R.string.notification_enabled)
        ToggleItem.WIFI_ONLY -> context.getString(R.string.wifi_only)
        ToggleItem.THEME -> context.getString(R.string.theme)
        ToggleItem.PERSONAL_INFORMATION -> context.getString(R.string.personal_information)
    }
}