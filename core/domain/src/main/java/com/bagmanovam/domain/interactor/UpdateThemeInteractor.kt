package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.model.Theme
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateThemeUseCase

class UpdateThemeInteractor(
    private val settingsRepository: SettingsRepository,
) : UpdateThemeUseCase {
    override suspend fun invoke(theme: Theme) {
        settingsRepository.updateTheme(theme)
    }
}