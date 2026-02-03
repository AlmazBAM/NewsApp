package com.bagmanov.sync

import android.content.Context
import com.bagmanovam.domain.usecase.StartRefreshDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RefreshWorkScheduler(
    private val context: Context,
    private val startRefreshDataUseCase: StartRefreshDataUseCase,
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)


    fun observeRefreshConfig() {
        startRefreshDataUseCase().onEach { config ->
            NewsWorkManagerInitializer.initialize(context, config)
        }.launchIn(scope)
    }
}