package com.example.infosec.viewmodel;


import androidx.lifecycle.ViewModel;

import com.example.infosec.model.Repository;

import javax.inject.Inject;

public class AppViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public AppViewModel(Repository repository) {
        this.repository = repository;
    }


}

