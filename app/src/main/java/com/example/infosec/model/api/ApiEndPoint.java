package com.example.infosec.model.api;


import com.example.infosec.model.api.response.NewsResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("top-headlines")
    Flowable<NewsResponse> getNewsFeed(@Query("q") String Query,@Query("apikey") String key);

}
