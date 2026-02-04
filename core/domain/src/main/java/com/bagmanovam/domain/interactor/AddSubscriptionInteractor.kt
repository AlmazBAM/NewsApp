package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.AddSubscriptionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddSubscriptionInteractor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository
) : AddSubscriptionUseCase {
    override suspend fun invoke(topic: String) {
        withContext(Dispatchers.IO) {
            newsRepository.addSubscription(topic)
            val settings = settingsRepository.getSettings().first()
            CoroutineScope(coroutineContext).launch {
                newsRepository.updateArticlesForTopic(topic, settings.language)
            }
        }
    }
}