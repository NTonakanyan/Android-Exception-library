package com.armboldmind.exceptionlibrary

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


object ExceptionHandler {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    val mScope = CoroutineScope(coroutineContext)
    lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
        if (getApplicationKey() == null)
            throw NullPointerException("ABM key not found")
    }

    fun setExceptionFollower(activity: AppCompatActivity) {
        Thread.setDefaultUncaughtExceptionHandler { paramThread, paramThrowable ->
            val str = "key " + getApplicationKey() + " \n" + "error  " + paramThrowable.message
        }
    }

    private fun getApplicationKey(): String? {
        val app: ApplicationInfo? = mContext.packageManager?.getApplicationInfo(mContext.packageName, PackageManager.GET_META_DATA)
        val bundle = app?.metaData
        return bundle?.getString("am.ABM.ApiKey")
    }
}