package com.example.chuolmvvm.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chuolmvvm.ui.fragment.SignInFragment;
import com.example.chuolmvvm.ui.fragment.SignUpFragment;

public class LoginRegisterViewPagerAdapter extends FragmentPagerAdapter {
    private int mNumTab;
    private SignUpFragment.SignUpFragmentListener mSignUpFragmentListener;
    private SignInFragment.SignInFragmentListener mSignInFragmentListener;

    public LoginRegisterViewPagerAdapter(FragmentManager fm, int numTab) {
        super(fm);
        mNumTab = numTab;
    }

    public void setSignUpListener(SignUpFragment.SignUpFragmentListener signUpListener) {
        mSignUpFragmentListener = signUpListener;
    }

    public void setSignInFragmentListener(SignInFragment.SignInFragmentListener signInFragmentListener){
        mSignInFragmentListener = signInFragmentListener;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SignInFragment.newInstance(mSignInFragmentListener);
            case 1:
                return SignUpFragment.newInstance(mSignUpFragmentListener);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumTab;
    }

}
