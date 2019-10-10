package com.example.chuolmvvm.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.databinding.FragmentPersonalInfoBinding;
import com.example.chuolmvvm.ui.fragment.AbsBindingFragment;
import com.example.chuolmvvm.viewmodel.PersonalFragmentViewModel;

import javax.inject.Inject;

public class PersonalFragment extends AbsBindingFragment<FragmentPersonalInfoBinding> {
    private static final String TAG = "PersonalFragment";

    @Inject
    ApiService mApiService;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getComponent().inject(this);
        setVariable(com.example.chuolmvvm.BR.viewModel,
                new PersonalFragmentViewModel(getContext(), new UserDataManager(mApiService)));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_personal_info;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }
}
