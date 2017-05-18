package com.sinhadroid.trillbit.app.module.login.model;

import android.content.Intent;

public interface LoginModel {
    void triggerGoogle();

    void triggerFacebook();

    void onGetGoogleResult(int requestCode, int resultCode, Intent data);

    void onGetFacebookResult(int requestCode, int resultCode, Intent data);
}
