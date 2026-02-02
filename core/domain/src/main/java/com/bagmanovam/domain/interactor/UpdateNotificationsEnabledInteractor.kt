package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.model.Theme
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateNotificationsEnabledUseCase
import com.bagmanovam.domain.usecase.UpdateThemeUseCase

class UpdateNotificationsEnabledInteractor(
    private val settingsRepository: SettingsRepository,
) : UpdateNotificationsEnabledUseCase {
    override suspend fun invoke(enabled: Boolean) {
        settingsRepository.updateNotificationsEnabled(enabled)
    }
}