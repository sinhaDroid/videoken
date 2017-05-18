package com.sinhadroid.trillbit.app.module.login.view;

import android.os.Bundle;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitActivity;
import com.sinhadroid.trillbit.app.module.login.presenter.LoginPresenter;
import com.sinhadroid.trillbit.app.module.login.presenter.LoginPresenterImpl;

/**
 * Created by deepanshu on 18/5/17.
 */

public class LoginActivity extends TrillbitActivity implements LoginView {

    private LoginPresenter mLoginPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        this.mLoginPresenter = LoginPresenterImpl.newInstance(this);
        mLoginPresenter.onCreate();
    }
}
