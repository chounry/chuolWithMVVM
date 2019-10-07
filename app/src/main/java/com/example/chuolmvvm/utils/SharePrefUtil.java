package com.example.chuolmvvm.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefUtil {
    private static final String ACCESS_TOKEN = "ACESS_TOKEN";
    private static final String REFRES_TOKEN = "REFRESH_TOKEN";
    private static final String USER_ID = "USER_ID";
    private static final String USERNAME = "USERNAME";
    private static final String EMAIL = "EMAIL";
    private static final String CHUOL = "CHUOL";

    public static void setToken(Context context, String accessToken, String refreshToken) {
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        sh.edit().putString(ACCESS_TOKEN, accessToken).apply();
        sh.edit().putString(REFRES_TOKEN, refreshToken).apply();
    }

    public static String getAccessToken(Context context) {
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        return sh.getString(ACCESS_TOKEN, null);
    }

    public static String getRefresToken(Context context) {
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        return sh.getString(REFRES_TOKEN, null);
    }

    public static void setUserId(Context context, String userId) {
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        sh.edit().putString(USER_ID, userId).apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        return sh.getString(USER_ID, null);
    }

    public static void setEmail(Context context, String email){
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        sh.edit().putString(EMAIL, email).apply();
    }

    public static String  getEmail(Context context){
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        return sh.getString(EMAIL, null);

    }

    public static void setUserName(Context context, String firstName, String lastName) {
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        sh.edit().putString(USERNAME, firstName + " " + lastName).apply();
    }

    public static String getUsername(Context context){
        SharedPreferences sh = context.getSharedPreferences(CHUOL,Context.MODE_PRIVATE);
        return sh.getString(USERNAME, null);
    }

    public static void clearAll(Context context){
        SharedPreferences sh = context.getSharedPreferences(CHUOL, Context.MODE_PRIVATE);
        sh.edit().clear().apply();
    }

    public static boolean isLogin(Context context){
        return SharePrefUtil.getAccessToken(context) != null;
    }
}
