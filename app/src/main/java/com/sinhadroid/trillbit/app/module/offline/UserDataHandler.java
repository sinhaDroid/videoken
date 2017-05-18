package com.sinhadroid.trillbit.app.module.offline;

import android.content.Context;
import android.content.SharedPreferences;

import com.sinhadroid.trillbit.app.module.common.AbstractDataHandler;

public class UserDataHandler extends AbstractDataHandler {

    private static UserDataHandler sUserDataHandler = new UserDataHandler();

    private SharedPreferences mSharedPreferences;

    public static UserDataHandler getInstance() {
        return sUserDataHandler;
    }

    private UserDataHandler() {
    }

    public void init(Context context) {
        mSharedPreferences = context.getSharedPreferences(UserDataHandler.class.getCanonicalName(), Context.MODE_PRIVATE);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}
