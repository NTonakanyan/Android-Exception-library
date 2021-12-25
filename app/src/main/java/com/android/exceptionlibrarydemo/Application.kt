package com.android.exceptionlibrarydemo

import android.app.Application
import com.android.exceptionlibrary.initExceptionHandler

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initExceptionHandler(this)
    }
}