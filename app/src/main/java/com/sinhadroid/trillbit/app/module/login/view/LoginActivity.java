package com.sinhadroid.trillbit.app.module.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitActivity;
import com.sinhadroid.trillbit.app.module.main.MainActivity;
import com.sinhadroid.trillbit.app.module.login.presenter.LoginPresenter;
import com.sinhadroid.trillbit.app.module.login.presenter.LoginPresenterImpl;

import butterknife.OnClick;

public class LoginActivity extends TrillbitActivity implements LoginView {

    private LoginPresenter mLoginPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        this.mLoginPresenter = LoginPresenterImpl.newInstance(this);
    }

    @OnClick(R.id.login_rl_google)
    public void onClickGoogle(View view) {
        mLoginPresenter.onClickGoogle();
    }

    @OnClick(R.id.login_rl_fb)
    public void onClickFacebook(View view) {
        mLoginPresenter.onClickFacebook();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void signInGoogle(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void navigateToHomeActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
