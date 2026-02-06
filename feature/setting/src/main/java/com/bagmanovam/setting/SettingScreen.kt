package com.bagmanovam.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bagmanovam.setting.component.PreferenceCard
import com.bagmanovam.setting.component.SettingCard
import com.bagmanovam.setting.component.SettingsSection

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    state: SettingState,
    onSettingAction: (SettingAction) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            item { SettingsSection(title = stringResource(R.string.preference_section)) }
            item {
                PreferenceCard(
                    state = state,
                    onSettingAction = onSettingAction
                )
            }
        }
    }
}
