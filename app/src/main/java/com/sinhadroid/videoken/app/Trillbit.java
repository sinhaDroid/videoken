package com.sinhadroid.videoken.app;

import android.app.Application;
import android.content.Context;

import com.sinhadroid.videoken.app.module.offline.VideoKenDataHandler;

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
    }

    private void initDataHandlers() {
        Context context = getApplicationContext();

        VideoKenDataHandler.getInstance().init(context);
    }
}
