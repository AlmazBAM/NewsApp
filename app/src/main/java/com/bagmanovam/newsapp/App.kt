package com.bagmanovam.newsapp

import android.app.Application
import androidx.work.Configuration
import com.bagmanov.sync.NewsWorkManagerInitializer
import com.bagmanovam.data.di.dataModule
import com.bagmanovam.data.di.networkModule
import com.bagmanovam.news.di.appModule
import com.bagmanovam.newsapp.di.platformModule
import com.bagmanovam.sync.di.syncModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class App : Application(), Configuration.Provider {
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(KoinWorkerFactory()).build()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            workManagerFactory()
            modules(appModule, platformModule, dataModule, networkModule, syncModule)
        }
        NewsWorkManagerInitializer.initialize(this)
    }
}