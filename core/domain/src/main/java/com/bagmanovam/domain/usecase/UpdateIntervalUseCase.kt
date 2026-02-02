package com.bagmanovam.domain.usecase

import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface UpdateIntervalUseCase {

    suspend operator fun invoke(minutes: Int)
}