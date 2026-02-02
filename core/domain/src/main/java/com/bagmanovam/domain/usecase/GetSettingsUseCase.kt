package com.bagmanovam.domain.usecase

import com.bagmanovam.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface GetSettingsUseCase {

    operator fun invoke(): Flow<Settings>
}