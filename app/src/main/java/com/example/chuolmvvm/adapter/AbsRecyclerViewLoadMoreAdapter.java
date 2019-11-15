package com.example.chuolmvvm.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.chuolmvvm.R;
import com.example.chuolmvvm.adapter.viewholder.BindingViewHolder;

import java.util.List;

public abstract class AbsRecyclerViewLoadMoreAdapter<D, VH extends BindingViewHolder> extends AbsRecyclerViewAdapter<D, VH> {

    private final int ITEM_LOAD_MORE = 0x001;
    private final int ITEM_NORMAL = 0x002;

    private boolean mCanLoadMore;

    public AbsRecyclerViewLoadMoreAdapter(List<D> data) {
        super(data);
    }

    public void setCanLoadMore(boolean canLoadMore) {
        mCanLoadMore = canLoadMore;
    }

    @Override
    public final int getItemCount() {
        return mData != null ? mCanLoadMore ? mData.size() + 1 : mData.size() : 0;
    }

    public abstract void onBindChildViewHolder(VH holder, int position);

    @Override
    public int getItemViewType(int position) {
        if (mCanLoadMore) {
            if (position == mData.size()) {
                return ITEM_LOAD_MORE;
            }
        }
        return ITEM_NORMAL;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_LOAD_MORE) {
            return (VH) new BindingViewHolder.Builder(parent, R.layout.item_load_more).build();
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (getItemViewType(position) != ITEM_LOAD_MORE) {
            onBindChildViewHolder(holder, position);
        }
    }

    public void addMoreItems(List<D> data) {
        int previousItemCount = getItemCount();
        mData.addAll(data);
        int itemCount = mCanLoadMore ? data.size() : data.size() - 1;
        notifyItemRangeInserted(previousItemCount, itemCount);
    }

    public void notifyLoadMoreLayoutRemoved(){
        notifyItemRemoved(getItemCount());
    }
}
