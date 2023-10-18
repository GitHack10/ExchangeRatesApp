package com.kamabd.logger

import android.util.Log

object AppLogger {

    private const val DEFAULT_TAG = "MIINE"

    fun logD(message: String, tag: String = DEFAULT_TAG) {
        Log.d(tag, message)
    }

    fun logE(message: String, tag: String = DEFAULT_TAG) {
        Log.e(tag, message)
    }
}