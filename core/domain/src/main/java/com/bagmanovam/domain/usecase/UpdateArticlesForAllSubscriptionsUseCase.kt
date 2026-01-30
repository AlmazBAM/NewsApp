package com.bagmanovam.domain.usecase

interface UpdateArticlesForAllSubscriptionsUseCase {
    suspend operator fun invoke(): List<String>
}