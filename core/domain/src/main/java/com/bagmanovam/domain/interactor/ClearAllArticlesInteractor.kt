package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.ClearAllArticlesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClearAllArticlesInteractor(
    private val newsRepository: NewsRepository
) : ClearAllArticlesUseCase {
    override suspend fun invoke(topics: List<String>) {
        withContext(Dispatchers.IO) { newsRepository.clearAllArticles(topics) }
    }
}