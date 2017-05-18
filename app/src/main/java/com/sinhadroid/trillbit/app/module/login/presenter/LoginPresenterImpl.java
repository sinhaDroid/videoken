package com.sinhadroid.trillbit.app.module.login.presenter;

import com.sinhadroid.trillbit.app.module.login.model.LoginModel;
import com.sinhadroid.trillbit.app.module.login.model.LoginModelImpl;
import com.sinhadroid.trillbit.app.module.login.view.LoginView;

/**
 * Created by deepanshu on 18/5/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;

    private LoginModel mLoginModel;

    private LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = LoginModelImpl.newInstance();
    }

    public static LoginPresenterImpl newInstance(LoginView loginView) {
        return new LoginPresenterImpl(loginView);
    }

    @Override
    public void onCreate() {

    }
}
