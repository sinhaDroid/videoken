package com.sinhadroid.trillbit.app.module.login.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jeeva on 4/7/16.
 */
public class LogInRequest {

    @JsonProperty("login_type")
    private String loginType;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("auth")
    private String auth;

    @JsonProperty("login_type")
    public String getLoginType() {
        return loginType;
    }

    @JsonProperty("login_type")
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("auth")
    public String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(String auth) {
        this.auth = auth;
    }
}