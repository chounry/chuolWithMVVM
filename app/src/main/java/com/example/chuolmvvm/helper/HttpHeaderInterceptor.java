package com.example.chuolmvvm.helper;

import android.content.Context;

import com.example.chuolmvvm.utils.SharePrefUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor {
    private static final String AUTHENTICATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private Context mContext;

    public HttpHeaderInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain
                .request()
                .newBuilder()
                .addHeader("accept", "application/json")
                .addHeader(AUTHENTICATION, BEARER + " " + SharePrefUtil.getAcessToken(mContext));

        Request request = builder.build();
        return chain.proceed(request);
    }
}
