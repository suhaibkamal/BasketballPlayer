package com.petraride.basketplayers

import android.app.Application
import com.petraride.basketplayers.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PlayerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PlayerApp)
            modules(appModule)
        }
    }
}