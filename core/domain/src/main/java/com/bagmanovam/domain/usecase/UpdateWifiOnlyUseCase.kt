package com.bagmanovam.domain.usecase

import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface UpdateWifiOnlyUseCase {

    suspend operator fun invoke(enabled: Boolean)
}