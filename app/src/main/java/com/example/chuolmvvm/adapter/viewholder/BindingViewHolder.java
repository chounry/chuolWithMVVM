package com.example.chuolmvvm.adapter.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mViewDataBinding;

    public static class Builder {
        @LayoutRes
        private int mLayoutRes;
        private ViewGroup mParent;

        public Builder(ViewGroup viewGroup, @LayoutRes int layoutRes) {
            mLayoutRes = layoutRes;
            mParent = viewGroup;
        }

        public BindingViewHolder build() {
            return new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(this.mParent.getContext()),
                    this.mLayoutRes, this.mParent, false));
        }

    }

    public BindingViewHolder(ViewDataBinding itemView) {
        super(itemView.getRoot());
        mViewDataBinding = itemView;
    }

    public ViewDataBinding getBinding() {
        return mViewDataBinding;
    }

    public void setVariable(int variable, Object value) {
        mViewDataBinding.setVariable(variable, value);
        mViewDataBinding.executePendingBindings();
    }
}
