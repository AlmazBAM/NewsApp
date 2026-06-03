package com.bagmanovam.analytics


interface AnalyticsTracker {
    fun logEvent(event: AnalyticsEvent)
}