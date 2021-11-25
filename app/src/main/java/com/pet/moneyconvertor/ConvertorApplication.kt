package com.pet.moneyconvertor

import android.app.Application
import androidx.work.*
import com.pet.moneyconvertor.workmanager.RefreshCurrencyWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

/*
  TODO: 25.11.2021. Overall comments:
        - no DI at all, even self implemented;
        - ignoring Google UI guidelines;
        - no unit tests;
        - no comments or documentation at all.
 */
class ConvertorApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        // FIXME: 25.11.2021 please, avoid a log leaks to release builds
        Timber.plant(Timber.DebugTree())
        startSetup()
    }

    private fun setupWorkManager() {
        val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<RefreshCurrencyWorker>(1, TimeUnit.HOURS)
                .setConstraints(constrains)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            RefreshCurrencyWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

    private fun startSetup(){
        applicationScope.launch {
            setupWorkManager()
        }
    }
}