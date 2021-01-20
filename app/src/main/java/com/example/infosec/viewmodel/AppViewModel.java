package com.example.infosec.viewmodel;


import androidx.lifecycle.ViewModel;

import com.example.infosec.model.Repository;
import com.example.infosec.model.api.response.NewsResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AppViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public AppViewModel(Repository repository) {
        this.repository = repository;
    }



    public Flowable<NewsResponse> getNewsFeed(){

        return repository.getNewsFeed();
    }
    }

