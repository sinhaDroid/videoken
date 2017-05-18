package com.sinhadroid.trillbit.app.module.login.presenter;

import android.content.Intent;

public interface LoginPresenter {
    void onClickGoogle();

    void onClickFacebook();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
