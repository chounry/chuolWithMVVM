package com.example.chuolmvvm.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.chuolmvvm.BR;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.databinding.ActivityPersonalInfoBinding;
import com.example.chuolmvvm.viewmodel.PersonalActivityViewModel;

import javax.inject.Inject;

public class PersonalActivity extends AbsBaseActivityBinding<ActivityPersonalInfoBinding> {

    @Inject
    ApiService mApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setVariable(BR.viewModel,
                new PersonalActivityViewModel(this, new UserDataManager(mApiService)));


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_personal_info;
    }
}
