package com.example.infosec.di.component;


import com.example.infosec.di.PerActivity;
import com.example.infosec.di.module.NetworkModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = NetworkModule.class)
public interface NetworkComponent {

  }
