package com.armboldmind.exceptionlibrary

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import android.provider.Settings
import kotlin.system.exitProcess


internal object ExceptionHandler {

    lateinit var mApplication: Application

    fun init(application: Application) {
        mApplication = application
    }

    @SuppressLint("HardwareIds")
    @JvmStatic
    fun setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val pair = try {
                val pInfo: PackageInfo = mApplication.packageManager.getPackageInfo(mApplication.packageName, 0)
                Pair(pInfo.versionName, pInfo.versionCode)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                Pair("unavailable", 0)
            }

            val s = "throwable -> ${throwable.message}\n" +
                    "manufacture -> ${Build.MANUFACTURER}\n" +
                    "deviceModel -> ${Build.MODEL}\n" +
                    "versionName -> ${pair.first}\n" +
                    "versionCode -> ${pair.second}\n" +
                    "deviceModel -> ${Build.MODEL}\n" +
                    "androidVersionCode -> ${Build.VERSION.SDK_INT}\n" +
                    "deviceId -> ${Settings.Secure.getString(mApplication.contentResolver, Settings.Secure.ANDROID_ID)}\n" +
                    "threadName -> ${thread.name}\n" +
                    "stackTrace -> ${throwable.stackTraceToString()}"

            val model = ErrorModel()
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


    private fun getApi(): Triple<String?, String?, String?> {
        val app: ApplicationInfo? = mApplication.packageManager?.getApplicationInfo(mApplication.packageName, PackageManager.GET_META_DATA)
        val bundle = app?.metaData
        val a = bundle?.getString("slack.api.a")
        val b = bundle?.getString("slack.api.b")
        val c = bundle?.getString("slack.api.c")
        return Triple(a, b, c)
    }
}