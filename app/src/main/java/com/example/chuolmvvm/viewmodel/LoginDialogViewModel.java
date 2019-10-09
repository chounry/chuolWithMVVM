package com.example.chuolmvvm.viewmodel;

import android.content.Context;

public class LoginDialogViewModel extends AbsBaseViewModel {
    private LoginDialogViewModelListener mLoginDialogViewModelListener;

    public LoginDialogViewModel(Context context, LoginDialogViewModelListener loginDialogViewModelListener) {
        super(context);
        mLoginDialogViewModelListener = loginDialogViewModelListener;
    }

    public void onLoginFacebookClick(){
        mLoginDialogViewModelListener.onLoginFacebookClicked();
    }

    public void onLoginSignUpClick(){
        mLoginDialogViewModelListener.onLoginSignUpClicked();
    }

    public interface LoginDialogViewModelListener{
        void onLoginSignUpClicked();

        void onLoginFacebookClicked();
    }

}
