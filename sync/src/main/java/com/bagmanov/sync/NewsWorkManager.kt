package com.bagmanov.sync

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "NewsWorkManager"
class NewsWorkManager(
    context: Context,
    params: WorkerParameters,
    private val updateArticlesForAllSubscriptionsUseCase: UpdateArticlesForAllSubscriptionsUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.i(TAG, "doWork: start", )
        try {
            updateArticlesForAllSubscriptionsUseCase()
            Log.i(TAG, "doWork: finish success")
            Result.success()
        } catch (e: Exception) {
            Log.i(TAG, "doWork: finish error ${e.message}")
            if (runAttemptCount < 3) Result.retry() else Result.failure()
        }

    }


}