package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.BR;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.databinding.FragmentSignupBinding;
import com.example.chuolmvvm.viewmodel.SignupViewModel;

public class SignupFragment extends AbsBindingFragment<FragmentSignupBinding> {
    private static final String TAG = "SignupFragment";

    private SignupViewModel mSignupViewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mSignupViewModel = new SignupViewModel();
        setVariable(BR.viewModel, mSignupViewModel);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_signup;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }
}
