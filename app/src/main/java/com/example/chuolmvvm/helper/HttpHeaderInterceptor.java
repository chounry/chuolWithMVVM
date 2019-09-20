package com.example.chuolmvvm.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor {
    private static final String AUTHENTICATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain
                .request()
                .newBuilder()
                .addHeader(AUTHENTICATION, "");

        Request request = builder.build();
        return chain.proceed(request);
    }
}
