package com.example.chuolmvvm.viewmodel;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import timber.log.Timber;

public class SignupViewModel extends AbsBaseViewModel {
    private ObservableField<String> mFirstName = new ObservableField<>();
    private ObservableField<String> mLastName = new ObservableField<>();
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<String> mPassword = new ObservableField<>();
    private ObservableField<String> mConPassword = new ObservableField<>();
    private ObservableField<String> mPhoneNumber = new ObservableField<>();
    private ObservableInt mSignupBtnVisibility = new ObservableInt();
    private ObservableInt mLoadingVisibility = new ObservableInt();

    public ObservableField<String> getPhoneNumber() {
        return mPhoneNumber;
    }

    public ObservableField<String> getFirstName() {
        return mFirstName;
    }

    public ObservableField<String> getLastName() {
        return mLastName;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableField<String> getPassword() {
        return mPassword;
    }

    public ObservableField<String> getConPassword() {
        return mConPassword;
    }

    public ObservableInt getSignupBtnVisibility() {
        return mSignupBtnVisibility;
    }

    public ObservableInt getLoadingVisibility() {
        return mLoadingVisibility;
    }

    public void onSignupClick(){
        Timber.i(mConPassword.get());
    }
}
