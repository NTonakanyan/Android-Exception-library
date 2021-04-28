package com.armboldmind.exceptionlibrary

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armboldmind.exceptionlibrary.databinding.ActivityExceptionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ExceptionActivity : AppCompatActivity() {

    private val _binding by lazy { ActivityExceptionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.handleWindowState()
        setContentView(_binding.root)

        _binding.restartApplication.setOnClickListener {
            val appPackage = intent.getStringExtra("packageName")
            if (appPackage != null) {
                val i = applicationContext.packageManager.getLaunchIntentForPackage(appPackage)
                i?.data = Uri.parse(appPackage)
                startActivity(i)
                finish()
            }
        }

        val model = intent.getParcelableExtra<ErrorModel>("model")
        val apiA = intent.getStringExtra("apiA") ?: ""
        val apiB = intent.getStringExtra("apiB") ?: ""
        val apiC = intent.getStringExtra("apiC") ?: ""
        val iService = RestService().getRetrofitInstance()

        val call = iService.loadChanges(apiA, apiB, apiC, model)
        call?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {}
            override fun onFailure(call: Call<Any?>, t: Throwable) {}
        })
    }
}