package com.armboldmind.exceptionlibrary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IExceptionSendDeveloperControllerAPI {
    @POST("/services/TD7B96B8Q/BK0GH7ENQ/H2eaqWo8QwtL5dGyZOd8Yctj")
    Call<Object> loadChanges(@Body ErrorModel model);
}