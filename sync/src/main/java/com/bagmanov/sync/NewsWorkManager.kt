package com.bagmanov.sync

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.bagmanovam.domain.model.RefreshConfig
import com.bagmanovam.domain.usecase.GetSettingsUseCase
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import com.bagmanovam.notifications.Notifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

private const val TAG = "NewsWorkManager"
class NewsWorkManager(
    context: Context,
    params: WorkerParameters,
    private val updateArticlesForAllSubscriptionsUseCase: UpdateArticlesForAllSubscriptionsUseCase,
    private val settingsUseCase: GetSettingsUseCase,
    private val notifier: Notifier
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.i(TAG, "doWork: start")
        try {
            updateArticlesForAllSubscriptionsUseCase().also { topics ->
                val settings = settingsUseCase().first()
                if (topics.isNotEmpty() && settings.showNotification)
                    notifier.postNotifications(topics)
            }
            Log.i(TAG, "doWork: finish success")
            Result.success()
        } catch (e: Exception) {
            Log.i(TAG, "doWork: finish error ${e.message}")
            if (runAttemptCount < 3) Result.retry() else Result.failure()
        }
    }

    companion object {
        fun startUpSyncWork(config: RefreshConfig) = PeriodicWorkRequestBuilder<NewsWorkManager>(
            repeatInterval = config.updateInterval.minutes.toLong(),
            repeatIntervalTimeUnit = TimeUnit.MINUTES,
        )
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(syncConstraints(config.wifiOnly))
            .build()
    }

}