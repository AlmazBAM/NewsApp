package com.bagmanovam.newsapp

import android.app.Application
import com.bagmanovam.news.di.appModule
import com.bagmanovam.data.di.dataModule
import com.bagmanovam.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(appModule, dataModule, networkModule)
        }
    }
}