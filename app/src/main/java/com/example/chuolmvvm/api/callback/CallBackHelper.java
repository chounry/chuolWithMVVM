package com.example.chuolmvvm.api.callback;

import android.content.Context;

import com.example.chuolmvvm.config.Constants;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class CallBackHelper<T extends Response<D>, D> {

    private Context mContext;
    private Observable<T> mTObservable;
    private Class<D> type;

    public CallBackHelper(Context context, Observable<T> TObservable) {
        mContext = context;
        mTObservable = TObservable;
    }


    public Disposable execute(final OnCallBackWithErrorBody<D> onCallBack) {
        return mTObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                    if (t.code() == Constants.SUCCESS_CODE) {
                        onCallBack.onComplete(t.body(), t.code());

                    } else if (t.code() == Constants.INVALID_CODE) {
                        onCallBack.onErrorBody(t.errorBody().string());

                    }

                }, throwable -> {
                    if(throwable instanceof SocketTimeoutException){

                    }
                    Timber.e("Throwable class name " + throwable.getClass().getSimpleName());
                    Timber.e("Error Message " + throwable.getMessage());
                });
    }
}
