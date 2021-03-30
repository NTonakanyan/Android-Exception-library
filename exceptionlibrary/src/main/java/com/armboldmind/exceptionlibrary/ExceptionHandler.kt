package com.armboldmind.exceptionlibrary

import android.accounts.AccountManager
import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import java.util.*
import kotlin.system.exitProcess


object ExceptionHandler {

    lateinit var mApplication: Application

    fun init(application: Application) {
        mApplication = application
        if (getApplicationKey() == null)
            throw NullPointerException("ABM key not found")
    }

    @JvmStatic
    fun setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val s = "throwable -> ${throwable.message}\n" +
                    "manufacture -> ${Build.MANUFACTURER}\n" +
                    "deviceModel -> ${Build.MODEL}\n" +
                    "threadName -> ${thread.name}\n" +
                    "stackTrace -> ${throwable.stackTraceToString()}"

            val model = ErrorModel()
            model.key = getApplicationKey()
            model.text = s
            model.manufacture = Build.MANUFACTURER
            model.deviceModel = Build.MODEL

            val stackTrace = throwable.cause?.stackTrace
            if (!stackTrace.isNullOrEmpty()) {
                model.className = stackTrace.firstOrNull()?.className
                model.crashLine = stackTrace.firstOrNull()?.lineNumber
            }
            val intent = Intent(mApplication, ExceptionActivity::class.java)
            intent.putExtra("model", model)
            intent.putExtra("packageName", mApplication.packageName)
            intent.putExtra("apiA", getApi().first)
            intent.putExtra("apiB", getApi().second)
            intent.putExtra("apiC", getApi().third)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            mApplication.startActivity(intent)
            Process.killProcess(Process.myPid())
            exitProcess(10)
        }
    }

    private fun getApplicationKey(): String? {
        val app: ApplicationInfo? = mApplication.packageManager?.getApplicationInfo(
            mApplication.packageName,
            PackageManager.GET_META_DATA
        )
        val bundle = app?.metaData
        return bundle?.getString("am.ABM.ApiKey")
    }

    private fun getApi(): Triple<String?, String?, String?> {
        val app: ApplicationInfo? = mApplication.packageManager?.getApplicationInfo(
            mApplication.packageName,
            PackageManager.GET_META_DATA
        )
        val bundle = app?.metaData
        val a = bundle?.getString("slack.api.a")
        val b = bundle?.getString("slack.api.b")
        val c = bundle?.getString("slack.api.c")
        return Triple(a, b, c)
    }
}