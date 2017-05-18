package com.sinhadroid.trillbit.app.module.login.model.facebook.url;

public class FacebookUrl {

    private static FacebookUrl facebookUrlFactory = new FacebookUrl();

    private String mBaseUrl = "https://graph.facebook.com/v2.5/";

    private FacebookUrl() {
    }

    public static FacebookUrl getFactory() {
        return facebookUrlFactory;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.mBaseUrl = apiBaseUrl;
    }
}