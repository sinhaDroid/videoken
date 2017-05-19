package com.sinhadroid.trillbit.app.webservice;

import com.sinhadroid.trillbit.app.module.login.model.dto.LogInRequest;
import com.sinhadroid.trillbit.app.module.login.model.dto.LogInResponse;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.RecordResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface TrillbitService {

    @POST("v1/login/")
    Call<LogInResponse> callLoginApi(@Body LogInRequest logInRequest);

    @Multipart
    @POST("v1/save_audio/")
    Call<RecordResponse> callAudioApi(@Part MultipartBody.Part part);
}