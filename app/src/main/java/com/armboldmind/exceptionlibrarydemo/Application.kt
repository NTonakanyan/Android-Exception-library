package com.armboldmind.exceptionlibrarydemo

import android.app.Application
import com.armboldmind.exceptionlibrary.ExceptionHandler
import com.armboldmind.exceptionlibrary.startExceptionHandler

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startExceptionHandler(this)
    }
}