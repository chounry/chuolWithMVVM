package com.example.chuolmvvm.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import timber.log.Timber;

public class SigninViewModel extends AbsBaseViewModel {
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();
    private ObservableInt mLoginBtnVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);

    public SigninViewModel(Context context) {
        super(context);
    }

    public void setEmail(ObservableField<String> email) {
        mEmail = email;
    }

    public void setPassword(ObservableField<String> password) {
        mPassword = password;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }

    public ObservableInt getLoginBtnVisibility() {
        return mLoginBtnVisibility;
    }

    public ObservableInt getLoadingVisibility() {
        return mLoadingVisibility;
    }

    public void onLoginClick(){
        Timber.i("Email " + mEmail.get() + "\nPassword " + mPassword.get());
    }

}
