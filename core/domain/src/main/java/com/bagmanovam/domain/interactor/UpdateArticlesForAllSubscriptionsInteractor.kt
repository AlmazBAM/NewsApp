package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import kotlinx.coroutines.flow.first

class UpdateArticlesForAllSubscriptionsInteractor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository
) : UpdateArticlesForAllSubscriptionsUseCase {
    override suspend fun invoke(): List<String> {
        val settings = settingsRepository.getSettings().first()
       return newsRepository.updateArticlesForAllSubscriptions(settings.language)
    }
}