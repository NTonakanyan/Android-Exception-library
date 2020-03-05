package com.armboldmind.exceptionlibrary

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestService {

    private var retrofit: Retrofit

    init {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://hooks.slack.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getRetrofitInstance(): IExceptionSendDeveloperControllerAPI {
        return retrofit.create(IExceptionSendDeveloperControllerAPI::class.java)
    }
}