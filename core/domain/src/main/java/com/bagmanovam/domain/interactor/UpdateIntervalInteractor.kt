package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateIntervalUseCase

class UpdateIntervalInteractor(
    private val settingsRepository: SettingsRepository,
) : UpdateIntervalUseCase {
    override suspend fun invoke(minutes: Int) {
        settingsRepository.updateUpdateInterval(minutes)
    }
}