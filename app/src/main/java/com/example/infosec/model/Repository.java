package com.example.infosec.model;


import android.content.Context;

import com.example.infosec.model.api.ApiEndPoint;
import com.example.infosec.model.api.response.NewsResponse;

import java.util.concurrent.Executor;

import io.reactivex.Flowable;

public class Repository {

    ApiEndPoint apiEndPoint;
     Executor executor;
    Context context;

    public Repository(ApiEndPoint apiEndPoint,
                      Executor executor, Context context) {
         this.executor = executor;
        this.context = context;
        this.apiEndPoint = apiEndPoint;
     }

    public Flowable<NewsResponse> getNewsFeed(){
        return apiEndPoint.getNewsFeed("Tech","6a9b5220551d48df81e9009e92626d40");
    }


}
