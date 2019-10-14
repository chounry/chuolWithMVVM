package com.example.chuolmvvm.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.databinding.ActivitySettingBinding;
import com.example.chuolmvvm.ui.fragment.PersonalFragment;
import com.example.chuolmvvm.ui.fragment.SettingFragment;
import com.example.chuolmvvm.utils.SharePrefUtil;
import com.example.chuolmvvm.viewmodel.SettingViewModel;

public class SettingActivity extends AbsActivityFragment<SettingFragment, ActivitySettingBinding>
        implements SettingViewModel.SettingViewModelListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingFragment settingFragment = new SettingFragment();
        settingFragment.setSettingViewModelListener(this);
        setFragment(R.id.container, settingFragment, settingFragment.getMyTag());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    public void onPersonalInfoClicked() {
        PersonalFragment fragment = new PersonalFragment();
        setFragmentOnTop(fragment, fragment.getMyTag(), true);
    }

    @Override
    public void onPostHistoryClicked() {

    }

    @Override
    public void onSignOutClicked() {
        SharePrefUtil.clearAll(getApplicationContext());
        finish();
    }

    @Override
    public void onAboutUsClicked() {

    }

    @Override
    public void onTermConClicked() {

    }


    @Override
    int getFragmentContainerId() {
        return R.id.container;
    }

    public void onBackClicked() {
        onBackPressed();
    }
}
