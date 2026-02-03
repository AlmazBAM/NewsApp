package com.bagmanovam.notifications.di

import com.bagmanovam.notifications.NewsNotifier
import com.bagmanovam.notifications.Notifier
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val notificationModule = module {
    singleOf(::NewsNotifier).bind<Notifier>()
}