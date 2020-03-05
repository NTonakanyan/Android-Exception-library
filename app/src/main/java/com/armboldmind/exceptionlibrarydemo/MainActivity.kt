package com.armboldmind.exceptionlibrarydemo

import android.os.Bundle

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        throw  NullPointerException("Test")
    }
}