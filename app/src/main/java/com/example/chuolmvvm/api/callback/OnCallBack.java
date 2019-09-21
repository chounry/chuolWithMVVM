package com.example.chuolmvvm.api.callback;

public interface OnCallBack<T> extends OnCompleteCallBackListener<T>{
    void onFail(Throwable throwable);
}
