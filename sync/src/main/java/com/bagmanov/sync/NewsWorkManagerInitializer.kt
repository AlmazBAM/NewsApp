package com.bagmanov.sync

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NewsWorkManagerInitializer {

    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                uniqueWorkName = SYNC_WORK_NAME,
                existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
                request = NewsWorkManager.startUpSyncWork()
            )
        }
    }
}

internal const val SYNC_WORK_NAME = "NewsSync WorkManager"