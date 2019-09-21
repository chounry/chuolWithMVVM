package com.example.chuolmvvm.injection;


import com.example.chuolmvvm.injection.scope.ApplicationScope;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {

    ControllerComponent getControllerComponent(NetworkModule networkModule);
}
