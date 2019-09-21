package com.example.chuolmvvm.api.callback;

public interface OnCallBackWithErrorBody<T> extends OnCallBack<T> {
    void onErrorBody(String errorBody);
}
