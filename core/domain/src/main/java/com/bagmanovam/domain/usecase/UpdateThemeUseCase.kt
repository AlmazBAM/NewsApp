package com.bagmanovam.domain.usecase

import com.bagmanovam.domain.model.Language
import com.bagmanovam.domain.model.Settings
import com.bagmanovam.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface UpdateThemeUseCase {

    suspend operator fun invoke(theme: Theme)
}