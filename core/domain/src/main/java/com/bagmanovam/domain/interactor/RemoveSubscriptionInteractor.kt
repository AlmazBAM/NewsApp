package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.RemoveSubscriptionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoveSubscriptionInteractor(
    private val newsRepository: NewsRepository
) : RemoveSubscriptionUseCase {
    override suspend fun invoke(topic: String) {
        withContext(Dispatchers.IO) {
            newsRepository.removeSubscription(topic)
        }
    }
}