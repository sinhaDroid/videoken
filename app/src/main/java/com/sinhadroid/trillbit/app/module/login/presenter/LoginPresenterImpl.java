package com.sinhadroid.trillbit.app.module.login.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.login.model.LoginModel;
import com.sinhadroid.trillbit.app.module.login.model.LoginModelImpl;
import com.sinhadroid.trillbit.app.module.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginModelImpl.LoginModelListener {

    private static final int GOOGLE_SIGN_IN_CODE = 9001;

    private LoginView mLoginView;

    private LoginModel mLoginModel;

    private LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = LoginModelImpl.newInstance(LoginPresenterImpl.this);
    }

    public static LoginPresenterImpl newInstance(LoginView loginView) {
        return new LoginPresenterImpl(loginView);
    }

    @Override
    public void onClickGoogle() {
        mLoginModel.triggerGoogle();
    }

    @Override
    public void onClickFacebook() {
        mLoginModel.triggerFacebook();
    }

    @Override
    public void onGoogleSignIn(Intent intent) {
        mLoginView.signInGoogle(intent, GOOGLE_SIGN_IN_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            if (requestCode == GOOGLE_SIGN_IN_CODE) {
                mLoginModel.onGetGoogleResult(requestCode, resultCode, data);
            } else {
                mLoginModel.onGetFacebookResult(requestCode, resultCode, data);
            }
        } else {
            onLoginCanceled();
        }
    }

    @Override
    public FragmentActivity getActivity() {
        return mLoginView.getActivity();
    }

    @Override
    public void onGettingData() {
        mLoginView.showLoading();
    }

    @Override
    public void onLoginCanceled() {
        mLoginView.hideLoading();
        mLoginView.showMessage(R.string.login_cancelled);
    }

    @Override
    public void onLoginFailed() {
        mLoginView.hideLoading();
        mLoginView.showMessage(R.string.login_failed);
    }

    @Override
    public void onLoginFailed(String message) {
        mLoginView.hideLoading();
        mLoginView.showMessage(message);
    }

    @Override
    public void onNoInternet() {
        mLoginView.hideLoading();
        mLoginView.showMessage(R.string.no_network_connection);
    }

    @Override
    public void onLoginCompleted() {
        mLoginView.hideLoading();
        mLoginView.navigateToHomeActivity();
    }
}
