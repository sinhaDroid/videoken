package com.sinhadroid.trillbit.app.module.login.model.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.sinhadroid.trillbit.app.module.login.model.facebook.user.GetProfileModelImpl;
import com.sinhadroid.trillbit.app.module.login.model.facebook.user.UserModelImpl;

import java.util.ArrayList;
import java.util.List;

public class FacebookHandler {

    private static final String TAG = "FacebookHandler";

    private static FacebookHandler sFacebookHandler;

    private CallbackManager mCallBackManager;

    private FacebookHandler() {
        mCallBackManager = CallbackManager.Factory.create();
    }

    public static FacebookHandler getInstance(Context context) {
        if (null == sFacebookHandler) {
            sFacebookHandler = new FacebookHandler();
            if (!FacebookSdk.isInitialized()) {
                Log.d(TAG, "FacebookSdk Initialized");
                FacebookSdk.sdkInitialize(context);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return sFacebookHandler;
    }

    public void login(Activity activity,
                      UserModelImpl.UserModelListener userModelListener,
                      List<String> readPermissions) {
        new UserModelImpl(userModelListener).loginUser(activity, mCallBackManager, readPermissions);
    }

    public boolean isLoggedIn() {
        return null != getAccessToken();
    }

    private AccessToken getAccessToken() {
        return AccessToken.getCurrentAccessToken();
    }

    public void getProfile(GetProfileModelImpl.GetProfileModelListener profileModelListener) {
        AccessToken accessToken = getAccessToken();
        new GetProfileModelImpl(profileModelListener).getProfileByPermissions(accessToken,
                new ArrayList<>(accessToken.getPermissions()));
    }

    public void logOut() {
        LoginManager.getInstance().logOut();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
    }
}