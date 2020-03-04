package com.armboldmind.exceptionlibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExceptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exception)

        val message = intent.getStringExtra("message")

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hooks.slack.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val IExceptionSendDeveloperControllerAPI = retrofit.create(IExceptionSendDeveloperControllerAPI::class.java)

        val model = ErrorModel()
        model.text = message

        val call = IExceptionSendDeveloperControllerAPI.loadChanges(model)

        call?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {}
            override fun onFailure(call: Call<Any?>, t: Throwable) {}
        })
    }
}