package com.example.chuolmvvm.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;

public class VisibilityBindingUtil {

    @BindingAdapter("visibility")
    public static void setVisibility(View view, ObservableInt visibility){
        view.setVisibility(visibility.get());
    }
}
