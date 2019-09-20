package com.example.chuolmvvm.viewmodel;

import android.content.Intent;

import androidx.databinding.BaseObservable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbsBaseViewModel extends BaseObservable {

    private CompositeDisposable mCompositeDisposable;

    public void onDestroy(){}

    public void onResume(){}

    public void onPause(){}

    public void onCreate(){}

    public void onActivityResult(int requestCode, int resultCode, Intent data){}

    public void clearDisposable(){
        if(mCompositeDisposable != null){
            mCompositeDisposable.clear();
        }
    }

    public void addDisposible(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
