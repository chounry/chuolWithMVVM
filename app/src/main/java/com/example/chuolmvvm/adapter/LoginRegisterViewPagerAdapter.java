package com.example.chuolmvvm.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chuolmvvm.ui.fragment.SigninFragment;
import com.example.chuolmvvm.ui.fragment.SignupFragment;

public class LoginRegisterViewPagerAdapter extends FragmentPagerAdapter {
    private int mNumTab;
    public LoginRegisterViewPagerAdapter(FragmentManager fm,int numTab) {
        super(fm);
        mNumTab = numTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SigninFragment();
            case 1:
                return new SignupFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumTab;
    }
}
