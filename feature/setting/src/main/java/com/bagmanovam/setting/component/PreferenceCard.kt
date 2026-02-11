package com.bagmanovam.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bagmanovam.domain.model.Theme
import com.bagmanovam.setting.SettingAction
import com.bagmanovam.setting.SettingState


@Composable
fun PreferenceCard(
    modifier: Modifier = Modifier,
    state: SettingState,
    onSettingAction: (SettingAction) -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    val items = listOf(Theme.SYSTEM, Theme.LIGHT, Theme.DARK)

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

        HorizontalDivider()


        Column(
            modifier = modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingRow(
                modifier = modifier.clickable { clicked = !clicked },
                item = ToggleItem.THEME
            )
            if (clicked) {
                SingleChoiceSegmentedButtonRow {
                    items.forEachIndexed { index, theme ->
                        SegmentedButton(
                            selected = index == selectedIndex,
                            onClick = {
                                selectedIndex = index
                                onSettingAction(SettingAction.UpdateTheme(theme))
                            },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = items.size,
                            )
                        ) {
                            Text(text = theme.name)
                        }
                    }
                }
            }

        }
    }
}