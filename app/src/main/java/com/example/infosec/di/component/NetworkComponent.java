package com.example.infosec.di.component;


import com.example.infosec.view.MainActivity;
import com.example.infosec.di.PerActivity;
import com.example.infosec.di.module.NetworkModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = NetworkModule.class)
public interface NetworkComponent {

  void inject(MainActivity mainActivity);
  }
