package com.armboldmind.exceptionlibrary

import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import kotlin.system.exitProcess

object ExceptionHandler {

    lateinit var mApplication: Application

    fun init(application: Application) {
        mApplication = application
        if (getApplicationKey() == null)
            throw NullPointerException("ABM key not found")
    }

    fun setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val model = ErrorModel()
            model.key = getApplicationKey()
            model.text = throwable.message
            model.manufacture = Build.MANUFACTURER
            model.deviceModel = Build.MODEL
            model.deviceModel = Build.MODEL


            val stackTrace = throwable.cause?.stackTrace
            if (!stackTrace.isNullOrEmpty()) {
                model.className = stackTrace[0]?.className
                model.crashLine = stackTrace[0]?.lineNumber!!
            }
            val intent = Intent(mApplication, ExceptionActivity::class.java)
            intent.putExtra("model", model)
            intent.putExtra("packageName",             mApplication.packageName)
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