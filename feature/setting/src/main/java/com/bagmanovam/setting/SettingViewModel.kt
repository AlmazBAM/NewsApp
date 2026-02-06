package com.bagmanovam.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingViewModel : ViewModel() {
    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    fun onAction(action: SettingAction) {
        when (action) {
            is SettingAction.ToggleNotification -> TODO()
            is SettingAction.ToggleWifiOnly -> TODO()
        }
    }
}