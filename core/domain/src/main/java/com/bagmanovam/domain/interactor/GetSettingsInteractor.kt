package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.GetSettingsUseCase

class GetSettingsInteractor(
    private val settingsRepository: SettingsRepository
): GetSettingsUseCase {
    override fun invoke() = settingsRepository.getSettings()
}