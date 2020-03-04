package com.armboldmind.exceptionlibrary

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IExceptionSendDeveloperControllerAPI {
    @POST("/services/TD7B96B8Q/BK0GH7ENQ/H2eaqWo8QwtL5dGyZOd8Yctj")
    fun loadChanges(@Body model: ErrorModel?): Call<Any?>?
}