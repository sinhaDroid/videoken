package com.sinhadroid.trillbit.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.sinhadroid.trillbit.app.module.offline.UserDataHandler;
import com.sinhadroid.trillbit.app.module.splash.SplashActivity;
import com.sinhadroid.trillbit.app.webservice.MyWebService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by deepanshu on 18/5/17.
 */

public class Trillbit extends Application {

    private static Trillbit sTrillbit;

    public static Trillbit getInstance() {
        return sTrillbit;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Assigning the SportsCafe instance
        sTrillbit = Trillbit.this;

        // Initializing all the data handlers
        initDataHandlers();

        // Initializing TrillbitWebService
        MyWebService.getInstance().init(BuildConfig.BASE_URL, getHeaderInterceptor());

        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Activating the Facebook analytics event tracking
        AppEventsLogger.activateApp(this);
    }

    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder reqBuilder = chain.request().newBuilder();

                reqBuilder.addHeader(Constants.ApiKeys.CONTENT_TYPE, "application/json");

                return chain.proceed(reqBuilder.build());
            }
        };
    }

    private void initDataHandlers() {
        Context context = getApplicationContext();

        UserDataHandler.getInstance().init(context);
    }

    public void logout() {
        clearAll();

        navigateToLogin();
    }

    private void clearAll() {
        UserDataHandler.getInstance().clearAll();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * Checking the internet connectivity
     *
     * @return true if the connection is available otherwise false
     */
    public boolean hasNetworkConnection() {
        NetworkInfo activeNetwork = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
