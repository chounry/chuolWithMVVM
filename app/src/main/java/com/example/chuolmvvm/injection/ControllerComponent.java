package com.example.chuolmvvm.injection;


import com.example.chuolmvvm.ui.activity.HomeActivity;
import com.example.chuolmvvm.ui.fragment.SignInFragment;
import com.example.chuolmvvm.ui.fragment.SignUpFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {NetworkModule.class})
public interface ControllerComponent {
    void inject(HomeActivity homeActivity);

    void inject(SignUpFragment signupFragment);

    void inject(SignInFragment signInFragment);

}
