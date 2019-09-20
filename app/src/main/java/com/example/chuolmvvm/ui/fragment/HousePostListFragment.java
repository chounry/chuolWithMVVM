package com.example.chuolmvvm.ui.fragment;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.databinding.FragmentPostListBinding;

public class HousePostListFragment extends AbsBindingFragment<FragmentPostListBinding> {
    private static final String TAG = "HousePostListFragment";
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_post_list;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }
}
