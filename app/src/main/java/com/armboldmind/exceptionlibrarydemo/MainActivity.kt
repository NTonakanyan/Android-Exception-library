package com.armboldmind.exceptionlibrarydemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armboldmind.exceptionlibrary.ExceptionHandler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ExceptionHandler.setUCEHandler()


        throw  NullPointerException("Test")
    }
}