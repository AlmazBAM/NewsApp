package com.bagmanov.sync

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.bagmanovam.domain.model.RefreshConfig

object NewsWorkManagerInitializer {

    fun initialize(context: Context, config: RefreshConfig) {
        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                uniqueWorkName = SYNC_WORK_NAME,
                existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                request = NewsWorkManager.startUpSyncWork(config)
            )
        }
    }
}

internal const val SYNC_WORK_NAME = "NewsSync WorkManager"