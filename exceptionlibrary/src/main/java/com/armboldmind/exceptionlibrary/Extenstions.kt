package com.armboldmind.exceptionlibrary

import android.app.Activity
import android.app.Application
import android.os.Bundle

fun startExceptionHandler(app: Application) {
    ExceptionHandler.init(app)
    app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            ExceptionHandler.setExceptionHandler()
//            if (activity is AppCompatActivity) {
//                activity.supportFragmentManager.addOnBackStackChangedListener {
//
//                }
//            }
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }
    })
}