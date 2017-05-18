package com.sinhadroid.trillbit.app.module.offline;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sinhadroid.trillbit.app.Constants;
import com.sinhadroid.trillbit.app.module.common.AbstractDataHandler;
import com.sinhadroid.trillbit.app.module.login.model.dto.Payload;
import com.sinhadroid.trillbit.app.webservice.MyWebService;

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

    public void setLoggedInUser(boolean loggedInUser) {
        setSharedBooleanData(Constants.SharedKeys.LOGGED_IN_USER, loggedInUser);
    }

    public boolean isLoggedInUser() {
        return getSharedBooleanData(Constants.SharedKeys.LOGGED_IN_USER, false);
    }

    public void savePayload(Payload payload) {
        setSharedStringData(Constants.SharedKeys.PAYLOAD, MyWebService.getInstance().getJsonStringFromObject(payload));
    }

    public Payload getPayload() {
        return MyWebService.getInstance()
                .getObjectFromJson(getSharedStringData(Constants.SharedKeys.PAYLOAD),
                        new TypeReference<Payload>() {
                        });
    }
}
