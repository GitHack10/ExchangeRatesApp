package com.kamabd.exchangeratesapp

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.kamabd.logger.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            AppLogger.logD("running on DEBUG_MODE ..")
        }
    }
}