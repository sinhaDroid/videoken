package com.sinhadroid.trillbit.app.module.login.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Payload {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("google_id")
    private String googleId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("facebook_id")
    private String facebookId;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    @Override
    public String toString() {
        return
                "Payload{" +
                        "access_token = '" + accessToken + '\'' +
                        ",google_id = '" + googleId + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",name = '" + name + '\'' +
                        ",email = '" + email + '\'' +
                        ",facebook_id = '" + facebookId + '\'' +
                        "}";
    }
}