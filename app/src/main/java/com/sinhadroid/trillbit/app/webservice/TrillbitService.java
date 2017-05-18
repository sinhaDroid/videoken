package com.sinhadroid.trillbit.app.webservice;

import com.sinhadroid.trillbit.app.module.login.model.dto.LogInRequest;
import com.sinhadroid.trillbit.app.module.login.model.dto.LogInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface TrillbitService {

    @POST("v1/login/")
    Call<LogInResponse> callLoginApi(@Body LogInRequest logInRequest);
}