package com.example.chuolmvvm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.chuolmvvm.ChuolApplication;
import com.example.chuolmvvm.R;
import com.example.chuolmvvm.injection.Component;

public abstract class AbsBaseActivity extends ResultPermissionActivity {
    private Component mComponent;

    public Component getComponent() {
        if (mComponent == null) {
            mComponent = ((ChuolApplication) getApplication()).getComponent();
        }
        return mComponent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setFragment(@IdRes int id, Fragment fragment, String tag) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void showActionBar(@DrawableRes int icon, final AbsBaseActiviyListener absBaseActiviyListener) {
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ImageButton navLeftBtn = v.findViewById(R.id.nav_left_btn);
        navLeftBtn.setImageResource(icon);

        navLeftBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(absBaseActiviyListener != null){
                    absBaseActiviyListener.onNavLeftBtnClicked();
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setCustomView(v);
    }

    public interface AbsBaseActiviyListener{
        void onNavLeftBtnClicked();
    }
}
