package com.example.chuolmvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chuolmvvm.R;

public class ChatOutFragment extends AbsBindingFragment {
    private static final String TAG = "ChatOutFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_chat_out;
    }

    @Override
    public String getMyTag() {
        return TAG;
    }


}
