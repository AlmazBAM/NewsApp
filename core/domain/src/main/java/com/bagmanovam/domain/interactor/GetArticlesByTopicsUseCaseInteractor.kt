package com.bagmanovam.domain.interactor

import com.bagmanovam.domain.model.Article
import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.usecase.GetArticlesByTopicsUseCase
import kotlinx.coroutines.flow.Flow

class GetArticlesByTopicsUseCaseInteractor(
    private val newsRepository: NewsRepository
) : GetArticlesByTopicsUseCase {
    override fun invoke(topics: List<String>): Flow<List<Article>> {
        return newsRepository.getArticlesByTopics(topics)
    }
}