package com.sinhadroid.videoken.app.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.sinhadroid.videoken.app.R;
import com.sinhadroid.videoken.app.utils.CommonUtils;

public class BaseActivity extends Activity implements BaseView {

    private ProgressDialog mProgressDialog;

    public Activity getActivity() {
        return BaseActivity.this;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showSnackBar(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        showSnackBar(message);
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    public void showSoftKeyboard(View view) {
        getInputMethodManager().showSoftInput(view, 0);
    }

    public void hideSoftKeyboard() {
        if (null != getCurrentFocus())
            getInputMethodManager().hideSoftInputFromWindow(getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
