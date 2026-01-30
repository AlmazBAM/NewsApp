package com.bagmanovam.domain.usecase

interface ClearAllArticlesUseCase {
     suspend operator fun invoke(topics: List<String>)
}