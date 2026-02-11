package com.bagmanovam.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.domain.usecase.GetSettingsUseCase
import com.bagmanovam.domain.usecase.UpdateNotificationsEnabledUseCase
import com.bagmanovam.domain.usecase.UpdateThemeUseCase
import com.bagmanovam.domain.usecase.UpdateWifiOnlyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingViewModel(
    private val updateNotificationsEnabledUseCase: UpdateNotificationsEnabledUseCase,
    private val updateWifiOnlyUseCase: UpdateWifiOnlyUseCase,
    private val updateThemeUseCase: UpdateThemeUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    fun onAction(action: SettingAction) {
        when (action) {
            is SettingAction.ToggleNotification -> {
                _state.update { state ->
                    state.copy(
                        notificationEnabled = action.enabled
                    )
                }
                viewModelScope.launch { updateNotificationsEnabledUseCase(action.enabled) }
            }
            is SettingAction.ToggleWifiOnly -> {
                _state.update { state ->
                    state.copy(
                        wifiOnly = action.enabled
                    )
                }
                viewModelScope.launch { updateWifiOnlyUseCase(action.enabled) }
            }

            is SettingAction.UpdateTheme -> {
                _state.update { state ->
                    state.copy(
                        theme = action.theme
                    )
                }
                viewModelScope.launch { updateThemeUseCase(action.theme) }
            }
        }
    }
}