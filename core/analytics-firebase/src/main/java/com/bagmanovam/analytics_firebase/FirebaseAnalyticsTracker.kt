package com.bagmanovam.analytics_firebase

import android.util.Log
import com.bagmanovam.analytics.AnalyticsEvent
import com.bagmanovam.analytics.AnalyticsTracker
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

class FirebaseAnalyticsTracker(private val analytics: FirebaseAnalytics) : AnalyticsTracker {
    override fun logEvent(
        event: AnalyticsEvent,
    ) {
        Log.d("FirebaseAnalyticsTracker", "Logging event: ${event.name} with params: ${event.params}")
        analytics.logEvent(event.name) {
            event.params?.forEach { (key, value) ->
                when (value) {
                    is Double -> param(key, value)
                    is String -> param(key.take(40), value.take(100))
                    is Long -> param(key, value)
                    else -> error("Unsupported param type ${value.javaClass} in key \"$key\"")
                }
            }
        }
    }
}

