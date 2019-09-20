package com.example.chuolmvvm.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chuolmvvm.ui.fragment.HousePostListFragment;
import com.example.chuolmvvm.ui.fragment.RoomPostListFragment;

public class HomePageAdapter extends FragmentPagerAdapter {
    private int mNumTab;
    public HomePageAdapter(FragmentManager fm,int numTab) {
        super(fm);
        mNumTab = numTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HousePostListFragment();
            case 1:
                return new RoomPostListFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumTab;
    }
}
