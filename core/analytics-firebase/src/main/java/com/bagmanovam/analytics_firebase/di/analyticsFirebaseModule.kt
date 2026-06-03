package com.bagmanovam.analytics_firebase.di

import com.bagmanovam.analytics.AnalyticsHelper
import com.bagmanovam.analytics.AnalyticsTracker
import com.bagmanovam.analytics_firebase.FirebaseAnalyticsTracker
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val analyticsFirebaseModule = module {

    single {
        FirebaseAnalyticsTracker(
            FirebaseAnalytics.getInstance(get())
        )
    }.bind<AnalyticsTracker>()

    single { AnalyticsHelper(getAll<AnalyticsTracker>()) }
}