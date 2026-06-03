package com.bagmanovam.analytics


class AnalyticsHelper(private val trackers: List<AnalyticsTracker>) : AnalyticsTracker {

    override fun logEvent(
        event: AnalyticsEvent
    ) {
        trackers.forEach {
            it.logEvent(event)
        }
    }
}