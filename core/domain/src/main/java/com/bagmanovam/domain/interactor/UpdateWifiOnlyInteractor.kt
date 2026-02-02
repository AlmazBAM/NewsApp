package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateWifiOnlyUseCase

class UpdateWifiOnlyInteractor(
    private val settingsRepository: SettingsRepository,
) : UpdateWifiOnlyUseCase {
    override suspend fun invoke(enabled: Boolean) {
        settingsRepository.updateWifiOnly(enabled)
    }
}