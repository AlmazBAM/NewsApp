package com.bagmanovam.domain.usecase

import com.bagmanovam.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface GetArticlesByTopicsUseCase {
    operator fun invoke(topics: List<String>): Flow<List<Article>>
}