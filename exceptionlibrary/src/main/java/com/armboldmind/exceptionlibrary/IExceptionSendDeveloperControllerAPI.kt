package com.armboldmind.exceptionlibrary

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

internal interface IExceptionSendDeveloperControllerAPI {
    @POST("/services/{apiA}/{apiB}/{apiC}")
    fun loadChanges(
        @Path("apiA") apiA: String,
        @Path("apiB") apiB: String,
        @Path("apiC") apiC: String,
        @Body model: ErrorModel?
    ): Call<Any?>?
}