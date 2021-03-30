package com.armboldmind.exceptionlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armboldmind.exceptionlibrary.databinding.ActivityExceptionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExceptionActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityExceptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityExceptionBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.restartApplication.setOnClickListener {
            val appPackage = intent.getStringExtra("packageName")
            if (appPackage != null) {
                val launchIntent = packageManager.getLaunchIntentForPackage(appPackage)
                startActivity(launchIntent)
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