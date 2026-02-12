package com.bagmanovam.setting.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bagmanovam.setting.SettingAction


@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    onSettingAction: (SettingAction) -> Unit
) {
    SettingCard {
        SettingRow(
            modifier = modifier,
            item = ToggleItem.NOTIFICATION_ENABLED
        ) {

        }

        HorizontalDivider()

        SettingRow(
            modifier = modifier,
            item = ToggleItem.PERSONAL_INFORMATION
        ) {

        }
    }
}