package com.example.infosec.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.infosec.di.PerActivity;
import com.example.infosec.model.Repository;

import javax.inject.Inject;

/*This class  is for dagger to work with viewmodel*/
@PerActivity
public class ViewModelFactory implements ViewModelProvider.Factory{

    @Inject
    Repository repository;

    @Inject
    public ViewModelFactory() {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AppViewModel.class)) {
            return (T) new AppViewModel(repository);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }

}
