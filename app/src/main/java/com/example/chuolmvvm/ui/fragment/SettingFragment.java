package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.BR;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.databinding.FragmentSettingBinding;
import com.example.chuolmvvm.viewmodel.SettingViewModel;

import timber.log.Timber;

public class SettingFragment extends AbsBindingFragment<FragmentSettingBinding> {
    private static final String TAG = "SettingFragment";

    private SettingViewModel mSettingViewModel;
    private SettingViewModel.SettingViewModelListener mSettingViewModelListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSettingViewModel = new SettingViewModel();
        mSettingViewModel.setSettingViewModelListener(mSettingViewModelListener);
        Timber.e("mSettingViewModel " + mSettingViewModelListener);
        setVariable(BR.viewModel, mSettingViewModel);
    }

    public void setSettingViewModelListener(SettingViewModel.SettingViewModelListener settingViewModelListener) {
        mSettingViewModelListener = settingViewModelListener;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_setting;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }

}
