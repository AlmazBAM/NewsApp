package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.GetAllSubscriptionsUseCase
import kotlinx.coroutines.flow.Flow

class GetAllSubscriptionsInteractor(
    private val newsRepository: NewsRepository
) : GetAllSubscriptionsUseCase {
    override fun invoke(): Flow<List<String>> {
        return newsRepository.getAllSubscriptions()
    }
}