package com.bagmanovam.setting.component

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.bagmanovam.setting.R


@Composable
fun SettingRow(
    modifier: Modifier = Modifier,
    item: ToggleItem,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = item.icon,
            contentDescription = item.toDisplayableString(context)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = item.toDisplayableString(context),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp
            )
        )
        content()
    }
}


enum class ToggleItem(val icon: ImageVector) {
    NOTIFICATION_ENABLED(Icons.Default.Notifications),
    WIFI_ONLY(Icons.Default.Wifi),

}

fun ToggleItem.toDisplayableString(context: Context): String {
    return when (this) {
        ToggleItem.NOTIFICATION_ENABLED -> context.getString(R.string.notification_enabled)
        ToggleItem.WIFI_ONLY -> context.getString(R.string.wifi_only)
    }
}