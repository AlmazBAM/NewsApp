package com.bagmanovam.domain.usecase

import com.bagmanovam.domain.model.RefreshConfig
import kotlinx.coroutines.flow.Flow

interface StartRefreshDataUseCase {

    operator fun invoke(): Flow<RefreshConfig>
}