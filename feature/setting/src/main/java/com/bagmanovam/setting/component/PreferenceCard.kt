package com.bagmanovam.setting.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bagmanovam.setting.SettingAction
import com.bagmanovam.setting.SettingState


@Composable
fun PreferenceCard(
    modifier: Modifier = Modifier,
    state: SettingState,
    onSettingAction: (SettingAction) -> Unit
) {
    SettingCard {
        SettingRow(
            modifier = modifier,
            item = ToggleItem.NOTIFICATION_ENABLED
        ) {
            Switch(
                checked = state.notificationEnabled,
                onCheckedChange = {
                    onSettingAction(SettingAction.ToggleNotification(it))
                },
            )
        }

        HorizontalDivider()

        SettingRow(
            modifier = modifier,
            item = ToggleItem.WIFI_ONLY
        ) {
            Switch(
                checked = state.wifiOnly,
                onCheckedChange = {
                    onSettingAction(SettingAction.ToggleWifiOnly(it))
                },
            )
        }

    }
}