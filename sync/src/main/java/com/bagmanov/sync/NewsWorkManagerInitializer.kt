package com.bagmanov.sync

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NewsWorkManagerInitializer {

    fun initialize(context: Context) {
        val request = PeriodicWorkRequestBuilder<NewsWorkManager>(
            repeatInterval = 15L, repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).build()
        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                uniqueWorkName = "NewsSync WorkManager",
                existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                request = request
            )
        }
    }
}