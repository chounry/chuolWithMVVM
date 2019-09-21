package com.example.chuolmvvm.model;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse{
    @SerializedName("token_type")
    private String mTokenType;
    @SerializedName("expires_in")
    private String mExpiresIn;
    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("refresh_token")
    private String mRefreshToken;

    public String getTokenType() {
        return mTokenType;
    }

    public String getExpiresIn() {
        return mExpiresIn;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }
}
