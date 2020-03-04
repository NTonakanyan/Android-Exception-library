package com.armboldmind.exceptionlibrarydemo

import android.app.Application
import com.armboldmind.exceptionlibrary.ExceptionHandler

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        ExceptionHandler.init(this)
    }
}