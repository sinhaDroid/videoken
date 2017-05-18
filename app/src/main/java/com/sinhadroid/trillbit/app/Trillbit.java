package com.sinhadroid.trillbit.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sinhadroid.trillbit.app.module.offline.UserDataHandler;
import com.sinhadroid.trillbit.app.webservice.MyWebService;

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
        MyWebService.getInstance().init(BuildConfig.BASE_URL);
    }

    private void initDataHandlers() {
        Context context = getApplicationContext();

        UserDataHandler.getInstance().init(context);
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
