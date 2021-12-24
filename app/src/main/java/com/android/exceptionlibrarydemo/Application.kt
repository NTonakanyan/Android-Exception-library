package com.android.exceptionlibrarydemo

import android.app.Application
import com.android.exceptionlibrary.startExceptionHandler

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startExceptionHandler(this)
    }
}