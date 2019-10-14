package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.api.ApiService;
import com.example.chuolmvvm.api.datamanager.UserDataManager;
import com.example.chuolmvvm.databinding.FragmentPersonalInfoBinding;
import com.example.chuolmvvm.ui.activity.SettingActivity;
import com.example.chuolmvvm.viewmodel.PersonalFragmentViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class PersonalFragment extends AbsBindingFragment<FragmentPersonalInfoBinding>
        implements PersonalFragmentViewModel.PersonalFragmentViewModelListener {
    private static final String TAG = "PersonalFragment";

    @Inject
    ApiService mApiService;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getComponent().inject(this);
        setVariable(com.example.chuolmvvm.BR.viewModel,
                new PersonalFragmentViewModel(getContext(),
                        new UserDataManager(mApiService), this));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_personal_info;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }

    @Override
    public void onBackClicked() {
        Timber.e("onBackClicked");
        if ((SettingActivity) getActivity() != null)
            ((SettingActivity) getActivity()).onBackClicked();
    }
}
