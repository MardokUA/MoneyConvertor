package com.pet.moneyconvertor

import android.app.Application
import timber.log.Timber

class ConvertorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}