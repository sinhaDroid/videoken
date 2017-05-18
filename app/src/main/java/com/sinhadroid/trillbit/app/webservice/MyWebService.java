package com.sinhadroid.trillbit.app.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MyWebService {

    private static MyWebService sMyWebService = new MyWebService();

    public static MyWebService getInstance() {
        return sMyWebService;
    }

    private TrillbitService mTrillbitService;

    private ObjectMapper mObjectMapper;

    public void init(String apiBaseUrl) {

        // Initialize Trillbit Api service using retrofit
        initTrillbitService(apiBaseUrl);
    }

    private void initTrillbitService(String baseUrl) {

        // Initialize ObjectMapper
        initObjectMapper();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(mObjectMapper))
                .build();
        mTrillbitService = retrofit.create(TrillbitService.class);
    }

    private OkHttpClient getCustomHttpClient(Interceptor requestInterceptor, InputStream inputStream) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        // add header as interceptor
        httpClient.addNetworkInterceptor(requestInterceptor);

        // TODO: remove
        /*try {
            httpClient.sslSocketFactory(addCertificate(inputStream));
        } catch (CertificateException | NoSuchAlgorithmException | IOException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }*/

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

    public <T> T getObjectFromJson(String json, TypeReference<T> typeReference) {
        try {
            return mObjectMapper.readValue(json, typeReference);
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
}