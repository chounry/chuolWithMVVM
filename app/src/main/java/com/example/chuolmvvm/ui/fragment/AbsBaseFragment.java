package com.example.chuolmvvm.ui.fragment;

import androidx.fragment.app.Fragment;

import com.example.chuolmvvm.ChuolApplication;
import com.example.chuolmvvm.injection.ControllerComponent;
import com.example.chuolmvvm.injection.NetworkModule;

public abstract class AbsBaseFragment extends Fragment {

    private ControllerComponent mComponent;


    public ControllerComponent getComponent() {
        if (mComponent == null) {
            mComponent = ((ChuolApplication) getActivity().getApplication()).getComponent()
                    .getControllerComponent(new NetworkModule(getContext()));
        }
        return mComponent;
    }

}
