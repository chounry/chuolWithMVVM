package com.example.chuolmvvm.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.chuolmvvm.viewmodel.AbsBaseViewModel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsBaseActivityBinding<T extends ViewDataBinding>
        extends AbsBaseActivity {
    private static final String TAG = "AbsBaseActivityBinding";
    private T mBinding;
    private List<AbsBaseViewModel> mAbsBaseViewModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Nothing Here");

        mBinding = DataBindingUtil.setContentView(this,getLayoutRes());
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    public void setVariable(int id,Object viewModel){
        mBinding.setVariable(id, viewModel);
    }

    public void addViewModel(AbsBaseViewModel viewModel){
        mAbsBaseViewModels.add(viewModel);
    }

    public T getBinding() {
        return mBinding;
    }
}
