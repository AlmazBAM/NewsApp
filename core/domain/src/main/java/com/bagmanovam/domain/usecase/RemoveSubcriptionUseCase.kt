package com.bagmanovam.domain.usecase

interface RemoveSubscriptionUseCase {
     suspend operator fun invoke(topic: String)
}