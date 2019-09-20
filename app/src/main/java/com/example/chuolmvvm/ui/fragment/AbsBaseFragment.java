package com.example.chuolmvvm.ui.fragment;

import androidx.fragment.app.Fragment;

import com.example.chuolmvvm.ChuolApplication;
import com.example.chuolmvvm.injection.Component;

public abstract class AbsBaseFragment extends Fragment {

    private Component mComponent;


    public Component getComponent(){
        if(mComponent == null){
            mComponent = ((ChuolApplication)getActivity().getApplication()).getComponent();
        }
        return mComponent;
    }

}
