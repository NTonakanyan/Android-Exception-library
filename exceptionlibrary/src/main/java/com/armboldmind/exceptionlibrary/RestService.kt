package com.armboldmind.exceptionlibrary

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class RestService {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val c = chain.request().newBuilder().build()
            val a = chain.proceed(c)
            a
        }
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://hooks.slack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getRetrofitInstance(): IExceptionSendDeveloperControllerAPI {
        return retrofit.create(IExceptionSendDeveloperControllerAPI::class.java)
    }
}