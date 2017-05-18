package com.sinhadroid.trillbit.app.module.login.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.sinhadroid.trillbit.app.Constants;
import com.sinhadroid.trillbit.app.Trillbit;
import com.sinhadroid.trillbit.app.module.login.model.dto.LogInRequest;
import com.sinhadroid.trillbit.app.module.login.model.dto.LogInResponse;
import com.sinhadroid.trillbit.app.module.login.model.facebook.FacebookHandler;
import com.sinhadroid.trillbit.app.module.login.model.facebook.user.FacebookPermission;
import com.sinhadroid.trillbit.app.module.login.model.facebook.user.FacebookProfile;
import com.sinhadroid.trillbit.app.module.login.model.facebook.user.GetProfileModelImpl;
import com.sinhadroid.trillbit.app.module.login.model.facebook.user.UserModelImpl;
import com.sinhadroid.trillbit.app.module.offline.UserDataHandler;
import com.sinhadroid.trillbit.app.webservice.MyWebService;
import com.sinhadroid.trillbit.app.webservice.TrillbitCallBack;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;

public class LoginModelImpl implements LoginModel, GoogleApiClient.OnConnectionFailedListener {

    private static final String SERVER_CLIENT_ID = "869911479338-vuf27bk4j3ts45aek7h826d55p0jdp7v.apps.googleusercontent.com";

    private static final String LOGIN_SCOPE = "https://www.googleapis.com/auth/plus.login";

    private static final String PROFILE_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";

    private static final String SCOPES = "oauth2:" + LOGIN_SCOPE + " " + PROFILE_SCOPE;

    private FacebookHandler mFacebookHandler;

    private GoogleApiClient mGoogleApiClient;

    private LoginModelListener mOnLoginModelListener;

    private LoginModelImpl(LoginModelListener listener) {
        this.mOnLoginModelListener = listener;

        this.mFacebookHandler = FacebookHandler.getInstance(Trillbit.getInstance());

        initGoogle();
    }

    public static LoginModelImpl newInstance(LoginModelListener listener) {
        return new LoginModelImpl(listener);
    }

    private void initGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(LOGIN_SCOPE))
                .requestIdToken(SERVER_CLIENT_ID)
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(mOnLoginModelListener.getActivity())
                .enableAutoManage(mOnLoginModelListener.getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        signOut();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .build();
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                    }
                });
    }

    @Override
    public void triggerGoogle() {
        if (Trillbit.getInstance().hasNetworkConnection()) {
            mOnLoginModelListener.onGoogleSignIn(Auth.GoogleSignInApi.getSignInIntent((mGoogleApiClient)));
        } else {
            mOnLoginModelListener.onNoInternet();
        }
    }

    @Override
    public void onGetGoogleResult(int requestCode, int resultCode, Intent data) {
        handleSignInResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data));
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (null != result) {
            Status status = result.getStatus();
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.

                callTrillbitLoginApi(getLoginRequestFromGoogle(result.getSignInAccount()));
                return;
            }
        }

        notifyGoogleLoginFailed();
    }

    private void notifyGoogleLoginFailed() {
        signOut();
        mOnLoginModelListener.onLoginFailed();
    }

    /*private void getGoogleAccessToken(final GoogleSignInAccount account) {
        mOnLoginModelListener.onGettingData();

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                try {
                    return GoogleAuthUtil.getToken(Trillbit.getInstance().getBaseContext(), account.getServerAuthCode(), SCOPES);
                    // TODO: remove coment line
                } catch (IOException transientEx) {
                    // Network or server error, try later
//                    ExceptionTracker.track(transientEx);
                } catch (UserRecoverableAuthException e) {
                    // Recover (with e.getIntent())
//                    ExceptionTracker.track(e);
                    mOnLoginModelListener.onGoogleSignIn(e.getIntent());
                } catch (GoogleAuthException authEx) {
                    // The call is not ever expected to succeed
                    // assuming you have already verified that
                    // Google Play services is installed.
//                    ExceptionTracker.track(authEx);
                }

                return null;
            }

            @Override
            protected void onPostExecute(String token) {
                if (null != token) {
                    Log.d("GPlus Token", token);

                    callTrillbitLoginApi(getLoginRequestFromGoogle(account));
                    return;
                }

                notifyGoogleLoginFailed();
            }

        };
        task.execute();
    }*/

    private LogInRequest getLoginRequestFromGoogle(GoogleSignInAccount account) {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setLoginType(Constants.AccessProviders.GOOGLE);
        logInRequest.setUserId(account.getId());
        logInRequest.setAuth(account.getIdToken());

        return logInRequest;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void triggerFacebook() {

        if (Trillbit.getInstance().hasNetworkConnection()) {
            // done facebook api request here
            if (mFacebookHandler.isLoggedIn()) {
                mFacebookHandler.logOut();
            }
            loginWithFacebook();
        } else {
            mOnLoginModelListener.onNoInternet();
        }
    }

    private void loginWithFacebook() {
        mFacebookHandler.login(mOnLoginModelListener.getActivity(), new UserModelImpl.UserModelListener() {
                    @Override
                    public void onFbLoginSuccess() {
                        getFbProfile();
                    }

                    @Override
                    public void onLoginCancelled() {
                        mOnLoginModelListener.onLoginCanceled();
                    }

                    @Override
                    public void onFbLoginFailed() {
                        mOnLoginModelListener.onLoginFailed();
                    }
                },
                Arrays.asList(FacebookPermission.EMAIL, FacebookPermission.PUBLIC_PROFILE, FacebookPermission.USER_FRIENDS));
    }

    @Override
    public void onGetFacebookResult(int requestCode, int resultCode, Intent data) {
        mFacebookHandler.onActivityResult(requestCode, resultCode, data);
    }

    private void getFbProfile() {
        mOnLoginModelListener.onGettingData();
        mFacebookHandler.getProfile(new GetProfileModelImpl.GetProfileModelListener() {
            @Override
            public void onGetProfileSuccess(FacebookProfile facebookProfile) {

                callTrillbitLoginApi(getLoginRequestFromFacebook(facebookProfile));
            }

            @Override
            public void onGetProfileFailed() {
                mOnLoginModelListener.onLoginFailed();
            }
        });
    }

    private LogInRequest getLoginRequestFromFacebook(FacebookProfile facebookProfile) {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setLoginType(Constants.AccessProviders.FACEBOOK);
        logInRequest.setUserId(facebookProfile.getId());
        logInRequest.setAuth(facebookProfile.getAccessToken());

        return logInRequest;
    }

    private void callTrillbitLoginApi(LogInRequest logInRequest) {

        MyWebService.getInstance().getLogInRequest(logInRequest)
                .enqueue(new TrillbitCallBack<LogInResponse>() {
                    @Override
                    public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                        super.onResponse(call, response);
                        if (response.isSuccessful()) {
                            handleLoginResponse(response.body());
                        } else {
                            mOnLoginModelListener.onLoginFailed(response.message());
                        }
                    }
                });
    }

    private void handleLoginResponse(LogInResponse logInResponse) {
        UserDataHandler dataHandler = UserDataHandler.getInstance();

        dataHandler.setLoggedInUser(true);
        dataHandler.savePayload(logInResponse.getPayload());

        mOnLoginModelListener.onLoginCompleted();;
    }

    public interface LoginModelListener {

        FragmentActivity getActivity();

        void onGettingData();

        void onLoginCompleted();

        void onLoginCanceled();

        void onLoginFailed();

        void onLoginFailed(String message);

        void onNoInternet();

        void onGoogleSignIn(Intent intent);
    }
}
