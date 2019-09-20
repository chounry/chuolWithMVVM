package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.BR;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.databinding.FragmentSigninBinding;
import com.example.chuolmvvm.viewmodel.SigninViewModel;

public class SigninFragment extends AbsBindingFragment<FragmentSigninBinding> {

    private static final String TAG = "SigninFragment";
    private SigninViewModel mSigninViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSigninViewModel = new SigninViewModel();
        setVariable(BR.viewModel, mSigninViewModel);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_signin;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }
}
