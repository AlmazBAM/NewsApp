package com.bagmanovam.domain.usecase

interface AddSubscriptionUseCase {
    suspend operator fun invoke(topic: String)
}