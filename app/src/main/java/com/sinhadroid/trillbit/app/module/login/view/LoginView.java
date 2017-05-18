package com.sinhadroid.trillbit.app.module.login.view;

import android.content.Intent;

import com.sinhadroid.trillbit.app.base.BaseView;

public interface LoginView extends BaseView {
    void signInGoogle(Intent intent, int requestCode);

    void navigateToHomeActivity();
}
