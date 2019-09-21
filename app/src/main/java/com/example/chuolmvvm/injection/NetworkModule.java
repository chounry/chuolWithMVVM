package com.example.chuolmvvm.injection;

import android.content.Context;

import com.example.chuolmvvm.config.Constants;
import com.example.chuolmvvm.helper.HttpHeaderInterceptor;
import com.example.chuolmvvm.api.ApiService;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {
    private Context mContext;

    public NetworkModule(Context context) {
        mContext = context;
    }

    @Provides
    public ApiService getApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    public Retrofit getRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(Constants.BASE_URL).build();
    }

    @Provides
    public Retrofit.Builder getBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    public OkHttpClient getOkHttpClientWithAuth(HttpLoggingInterceptor httpLoggingInterceptor, HttpHeaderInterceptor httpHeaderInterceptor) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(httpHeaderInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    public HttpHeaderInterceptor getHttpHeaderInterceptor() {
        return new HttpHeaderInterceptor(mContext);
    }

    @Provides
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(message -> Timber.i(message));
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logger;
    }

    @Provides
    public Gson getGson() {
        return new Gson();
    }
}
