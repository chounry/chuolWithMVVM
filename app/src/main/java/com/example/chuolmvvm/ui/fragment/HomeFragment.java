package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.adapter.HomePageAdapter;
import com.example.chuolmvvm.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends AbsBindingFragment<FragmentHomeBinding> {
    private static final String TAG = "HomeFragment";

    private HomePageAdapter mHomePageAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTabLayout();
    }

    private void initTabLayout() {
        final ViewPager viewPager = getBinding().viewPageHome;
        TabLayout tableLayout = getBinding().tabH;

        mHomePageAdapter = new HomePageAdapter(getChildFragmentManager(), tableLayout.getTabCount());
        viewPager.setAdapter(mHomePageAdapter);
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
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }
}
