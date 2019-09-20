package com.example.chuolmvvm.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.adapter.LoginRegisterViewPagerAdapter;
import com.example.chuolmvvm.databinding.ActivityLoginRegisterBinding;
import com.google.android.material.tabs.TabLayout;

public class LoginRegisterActivity extends AbsBaseActivityBinding<ActivityLoginRegisterBinding> {
    private LoginRegisterViewPagerAdapter mLoginRegisterViewPagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = getBinding().appBarInclude.toolbarLoginResgister;
        setSupportActionBar(toolbar);
        showActionBar(R.drawable.ic_clear_white_24dp, new AbsBaseActiviyListener() {
            @Override
            public void onNavLeftBtnClicked() {
                finish();
            }
        });
        initTabLayout();
    }

    private void initTabLayout(){
        final TabLayout tableLayout = getBinding().appBarInclude.contentLoginRegister.loginSignupTabLayout;
        final ViewPager viewPager = getBinding().appBarInclude.contentLoginRegister.viewPageHome;

        mLoginRegisterViewPagerAdapter = new LoginRegisterViewPagerAdapter(getSupportFragmentManager(),tableLayout.getTabCount());
        viewPager.setAdapter(mLoginRegisterViewPagerAdapter);
        viewPager.setOffscreenPageLimit(0);

        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login_register;
    }
}
