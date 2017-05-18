package com.sinhadroid.trillbit.app.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitActivity;
import com.sinhadroid.trillbit.app.module.home.view.HomeActivity;
import com.sinhadroid.trillbit.app.module.login.view.LoginActivity;
import com.sinhadroid.trillbit.app.module.offline.UserDataHandler;

public class SplashActivity extends TrillbitActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserDataHandler.getInstance().isLoggedInUser()) {
                    navigateToHome();
                } else {
                    navigateToLogIn();
                }
            }
        }, 2000);
    }

    private void navigateToHome() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }

    private void navigateToLogIn() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
