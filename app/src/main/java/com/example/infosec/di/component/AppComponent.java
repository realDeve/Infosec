package com.example.infosec.di.component;


import com.example.infosec.di.module.AppModule;
import com.example.infosec.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    NetworkComponent appComponent(NetworkModule networkModule);

}
