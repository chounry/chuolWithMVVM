package com.example.chuolmvvm;

import androidx.multidex.MultiDexApplication;

import com.example.chuolmvvm.injection.Component;
import com.example.chuolmvvm.injection.DaggerComponent;

import timber.log.Timber;

public class ChuolApplication extends MultiDexApplication {
    private Component mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    public Component getComponent(){
        if(mComponent == null){
            mComponent = DaggerComponent.builder().build();
        }
        return mComponent;
    }
}
