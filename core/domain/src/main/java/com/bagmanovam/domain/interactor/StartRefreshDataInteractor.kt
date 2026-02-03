package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.model.RefreshConfig
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.StartRefreshDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class StartRefreshDataInteractor(
    private val settingsRepository: SettingsRepository,
) : StartRefreshDataUseCase {
    override fun invoke(): Flow<RefreshConfig> {
        return settingsRepository.getRefreshConfigSettings().distinctUntilChanged()
    }
}