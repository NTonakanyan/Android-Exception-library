package com.armboldmind.exceptionlibrary

import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Process
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

object ExceptionHandler {

    lateinit var mApplication: Application

    fun init(application: Application) {
        mApplication = application
        if (getApplicationKey() == null)
            throw NullPointerException("ABM key not found")
    }

    fun setUCEHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val intent = Intent(mApplication, ExceptionActivity::class.java)
            intent.putExtra("message", throwable.message)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            mApplication.startActivity(intent)
            Process.killProcess(Process.myPid())
            exitProcess(10)
        }
    }

    private fun getApplicationKey(): String? {
        val app: ApplicationInfo? = mApplication.packageManager?.getApplicationInfo(mApplication.packageName, PackageManager.GET_META_DATA)
        val bundle = app?.metaData
        return bundle?.getString("am.ABM.ApiKey")
    }
}