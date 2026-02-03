package com.bagmanovam.notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.content.pm.PackageManager.PERMISSION_GRANTED


private const val NOTIFICATION_ID = 1
private const val NOTIFICATION_CHANNEL_ID = "ARTICLES_CHANNEL"
private const val NOTIFICATION_GROUP = "NEWS_NOTIFICATIONS_GROUP"
private const val NOTIFICATION_REQUEST_CODE = 0
private const val TARGET_ACTIVITY_NAME = "com.bagmanovam.news.MainActivity"

class NewsNotifier(
    private val context: Context,
) : Notifier {
    override fun postNotifications(topics: List<String>) = with(context) {

        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PERMISSION_GRANTED) return
        val notificationManager = NotificationManagerCompat.from(this)

        val summaryNotification = createNotification {
            val title = getString(
                R.string.update_subscriptions,
                topics.size,
                topics.joinToString(", ")
            )
            setSmallIcon(R.drawable.news)
                .setContentTitle(getString(R.string.news_updates))
                .setContentText(title)
                .setGroup(NOTIFICATION_GROUP)
                .setContentIntent(createPendingIntent())
                .setAutoCancel(true)
                .build()
        }

        notificationManager.notify(NOTIFICATION_ID, summaryNotification)
    }


    private fun Context.createNotification(
        block: NotificationCompat.Builder.() -> Unit,
    ): Notification {
        createNotificationChannel()
        return NotificationCompat.Builder(
            this,
            NOTIFICATION_CHANNEL_ID
        )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .apply(block)
            .build()
    }

    private fun Context.createNotificationChannel() {
        if (VERSION.SDK_INT < VERSION_CODES.O) return

        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            getString(R.string.news_updates_channel),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }

    fun Context.createPendingIntent(): PendingIntent =
        PendingIntent.getActivity(
            this,
            NOTIFICATION_REQUEST_CODE,
            Intent().apply {
                component = ComponentName(packageName, TARGET_ACTIVITY_NAME)
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
}