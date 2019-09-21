package com.example.chuolmvvm.injection;


import com.example.chuolmvvm.ui.activity.HomeActivity;
import com.example.chuolmvvm.ui.fragment.SignUpFragment;

@dagger.Component(modules = {NetworkModule.class})
public interface Component {
    void inject(HomeActivity homeActivity);

    void inject(SignUpFragment signupFragment);
}
