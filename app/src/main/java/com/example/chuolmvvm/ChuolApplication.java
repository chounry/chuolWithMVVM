package com.example.chuolmvvm;

import androidx.multidex.MultiDexApplication;

import com.example.chuolmvvm.injection.ApplicationComponent;
import com.example.chuolmvvm.injection.DaggerApplicationComponent;

import timber.log.Timber;

public class ChuolApplication extends MultiDexApplication {
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    public ApplicationComponent getComponent(){
        if(mComponent == null){
            mComponent = DaggerApplicationComponent.builder().build();
        }
        return mComponent;
    }
}
