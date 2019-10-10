package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class AbsBindingFragment<T extends ViewDataBinding> extends AbsBaseFragment {
    private T mBinding;
//    private AbsBaseViewModel mAbsBaseViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return getBinding().getRoot();
    }

    public T getBinding() {
        return mBinding;
    }

    public void setVariable(int id, Object object) {
        mBinding.setVariable(id, object);
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract String getMyTag();

}
