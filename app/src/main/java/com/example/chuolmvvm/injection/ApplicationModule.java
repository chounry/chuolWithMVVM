package com.example.chuolmvvm.injection;


import android.app.Application;

import com.example.chuolmvvm.injection.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @ApplicationScope
    @Provides
    Application getApplication() {
        return mApplication;
    }
}
