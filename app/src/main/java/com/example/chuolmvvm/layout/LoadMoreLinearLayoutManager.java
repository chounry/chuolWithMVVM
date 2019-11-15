package com.example.chuolmvvm.layout;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import timber.log.Timber;

public class LoadMoreLinearLayoutManager extends LinearLayoutManager {

    public LoadMoreLinearLayoutManager(Context context) {
        super(context, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Timber.e("dy " + dy +  "Recycler state " + state);
        return super.scrollVerticallyBy(dy, recycler, state);
    }
}
