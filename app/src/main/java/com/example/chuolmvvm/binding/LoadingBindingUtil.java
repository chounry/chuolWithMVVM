package com.example.chuolmvvm.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.victor.loading.rotate.RotateLoading;

public final class LoadingBindingUtil {

    @BindingAdapter("isLoading")
    public static void isLoading(RotateLoading view, int visibility){
        if(visibility == View.VISIBLE){
            view.start();
        }else{
            view.stop();
        }
    }
}
