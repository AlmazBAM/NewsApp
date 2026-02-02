package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateLanguageUseCase

class UpdateLanguageInteractor(
    private val settingsRepository: SettingsRepository,
) : UpdateLanguageUseCase {
    override suspend fun invoke(language: Language) {
        settingsRepository.updateLanguage(language)
    }
}