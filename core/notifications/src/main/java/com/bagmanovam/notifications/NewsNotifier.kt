package com.bagmanovam.notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.content.pm.PackageManager.PERMISSION_GRANTED


private const val NOTIFICATION_ID = ""
private const val NOTIFICATION_CHANNEL_ID = "ARTICLES_CHANNEL"
private const val NOTIFICATION_GROUP = "NEWS_NOTIFICATIONS_GROUP"

class NewsNotifier(
    private val context: Context,
) : Notifier {
    override fun postNotifications(topics: List<String>) = with(context) {

        val notificationManager = NotificationManagerCompat.from(this)

        val summaryNotification = createNotification {
            val title = getString(
                R.string.update_subscriptions,
                topics.size,
                topics.joinToString(", ")
            )
            setContentTitle(getString(R.string.news_updates))
                .setContentText(title)
                .setGroup(NOTIFICATION_GROUP)
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
}