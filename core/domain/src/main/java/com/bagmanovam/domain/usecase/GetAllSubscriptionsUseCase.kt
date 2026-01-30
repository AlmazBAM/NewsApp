package com.bagmanovam.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetAllSubscriptionsUseCase {
    operator fun invoke(): Flow<List<String>>
}