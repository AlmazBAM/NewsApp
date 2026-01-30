package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase

class UpdateArticlesForAllSubscriptionsInteractor(
    private val newsRepository: NewsRepository
) : UpdateArticlesForAllSubscriptionsUseCase {
    override suspend fun invoke(): List<String> {
       return newsRepository.updateArticlesForAllSubscriptions()
    }
}