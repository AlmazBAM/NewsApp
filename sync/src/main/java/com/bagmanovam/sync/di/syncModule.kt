package com.bagmanovam.sync.di

import android.util.Log
import com.bagmanov.sync.NewsWorkManager
import com.bagmanov.sync.RefreshWorkScheduler
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val syncModule = module {
    worker {
        Log.d("KOIN", "Worker created via Koin")

        NewsWorkManager(
            context = get(),
            params = get(),
            updateArticlesForAllSubscriptionsUseCase = get(),
        )
    }

    single(createdAtStart = true) {
        RefreshWorkScheduler(get(), get()).also {
            it.observeRefreshConfig()
        }
    }
}
