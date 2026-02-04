package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class UpdateArticlesForAllSubscriptionsInteractor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository,
) : UpdateArticlesForAllSubscriptionsUseCase {
    override suspend fun invoke() = withContext(Dispatchers.IO) {
        val settings = settingsRepository.getSettings().first()
        return@withContext newsRepository.updateArticlesForAllSubscriptions(settings.language)
    }
}