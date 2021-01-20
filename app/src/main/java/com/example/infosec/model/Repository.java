package com.example.infosec.model;


import android.content.Context;

import com.example.infosec.model.api.ApiEndPoint;

import java.util.concurrent.Executor;

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


}
