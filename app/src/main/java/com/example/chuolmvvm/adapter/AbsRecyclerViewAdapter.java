package com.example.chuolmvvm.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuolmvvm.adapter.viewholder.BindingViewHolder;

import java.util.List;

public abstract class AbsRecyclerViewAdapter<D,VH extends BindingViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<D> mData;

    public abstract int getLayoutRes();

    public AbsRecyclerViewAdapter(List<D> data) {
        mData = data;
    }

    public D getItem(int position) {
        if(mData == null || position > mData.size() - 1){
            return null;
        }
        return mData.get(position);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (VH) new BindingViewHolder.Builder(parent, getLayoutRes()).build();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
