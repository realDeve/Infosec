package com.example.infosec.di.module;

/*Module for the other module to access the  conext */

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Context context;

    @Inject
    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    @Inject
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton

    public Executor executor(){
        return Executors.newSingleThreadExecutor();
    }

}
