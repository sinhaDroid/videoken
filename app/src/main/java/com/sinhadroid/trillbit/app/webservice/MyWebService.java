package com.sinhadroid.trillbit.app.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.sinhadroid.trillbit.app.module.converter.JacksonConverterFactory;
import com.sinhadroid.trillbit.app.module.login.model.dto.LogInRequest;
import com.sinhadroid.trillbit.app.module.login.model.dto.LogInResponse;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.RecordResponse;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MyWebService {

    private static MyWebService sMyWebService = new MyWebService();

    public static MyWebService getInstance() {
        return sMyWebService;
    }

    private TrillbitService mTrillbitService;

    private ObjectMapper mObjectMapper;

    public void init(String apiBaseUrl, Interceptor header) {

        // Initialize Trillbit Api service using retrofit
        initTrillbitService(apiBaseUrl, header);
    }

    private void initTrillbitService(String baseUrl, Interceptor header) {

        // Initialize ObjectMapper
        initObjectMapper();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(mObjectMapper))
                .client(getCustomHttpClient(header))
                .build();
        mTrillbitService = retrofit.create(TrillbitService.class);
    }

    private OkHttpClient getCustomHttpClient(Interceptor header) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        // add header as interceptor
        httpClient.addNetworkInterceptor(header);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        return httpClient.build();

    }

    private void initObjectMapper() {
        mObjectMapper = new ObjectMapper();
        mObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> String getJsonStringFromObject(T object) {
        try {
            return mObjectMapper.writeValueAsString(object);
        } catch (Exception e) {
            // TODO: Crashlytics
//            ExceptionTracker.track(e);
        }
        return null;
    }

    public <T> T getObjectFromJson(String json, TypeReference<T> typeReference) {
        try {
            return mObjectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            // TODO: Crashlytics
//            ExceptionTracker.track(e);
        }
        return null;
    }

    public <T> T getObjectFromJson(String json, Class<T> classType) {
        try {
            return mObjectMapper.readValue(json, classType);
        } catch (Exception e) {
            // TODO: Crashlytics
//            ExceptionTracker.track(e);
        }
        return null;
    }

    public <T> T getObjectFromJson(String json, CollectionType classType) {
        try {
            return mObjectMapper.readValue(json, classType);
        } catch (Exception e) {
            // TODO: Crashlytics
//            ExceptionTracker.track(e);
        }
        return null;
    }

    public <T> T getObjectFromObject(Object object, Class<T> classType) {
        try {
            return (T) mObjectMapper.convertValue(object, Map.class);
        } catch (Exception e) {
            // TODO: Crashlytics
//            ExceptionTracker.track(e);
        }
        return null;
    }

    public Call<LogInResponse> getLogInRequest(LogInRequest logInRequest) {
        return mTrillbitService.callLoginApi(logInRequest);
    }

    public Call<RecordResponse> callAudioApi(MultipartBody.Part part) {
        return mTrillbitService.callAudioApi(part);
    }
}