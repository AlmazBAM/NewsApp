package com.bagmanovam.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagmanovam.setting.component.AccountCard
import com.bagmanovam.setting.component.PreferenceCard
import com.bagmanovam.setting.component.SettingsSection
import com.bagmanovam.ui.NewsAppTheme

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    state: SettingState,
    onSettingAction: (SettingAction) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.matchParentSize()
        ) {

            item {
                SettingsSection(
                    modifier = Modifier.padding(bottom = 12.dp),
                    title = stringResource(R.string.account_section)
                )
            }

            item {
                AccountCard(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    state = state,
                    onSettingAction = onSettingAction
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                SettingsSection(
                    modifier = Modifier.padding(bottom = 12.dp),
                    title = stringResource(R.string.preference_section)
                )
            }
            item {
                PreferenceCard(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    state = state,
                    onSettingAction = onSettingAction
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    NewsAppTheme {
        SettingScreen(
            state = SettingState(),
            onSettingAction = {}
        )
    }
}