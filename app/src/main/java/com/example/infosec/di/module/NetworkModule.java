package com.example.infosec.di.module;



/*The module responsible for various class to usehttp://techhost.accessng.com/TosinToba/etz_switchit/etz_payment_cron.php/*/


import android.content.Context;

import com.example.infosec.di.PerActivity;
import com.example.infosec.model.Repository;
import com.example.infosec.model.api.ApiEndPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    String baseurl;

    public NetworkModule(String baseurl) {
        this.baseurl = baseurl;
    }

    @Provides
    @PerActivity
    public Gson gson(){
        return  new GsonBuilder()
                .setLenient()
                .create();
    }



    @Provides
    @PerActivity
    @Inject
    public Retrofit getRerofit(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(okHttpClient)
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @PerActivity
    public HttpLoggingInterceptor  logging(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
    @Provides
    @PerActivity
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

    }


    @Provides
    @PerActivity
    @Inject
    public ApiEndPoint airtimeEndPoint(Retrofit retrofit){
        return retrofit.create(ApiEndPoint.class);
    }



    @Provides
    @PerActivity
    public CompositeDisposable compositeDisposable(){
        return new CompositeDisposable();
    }


    @Provides
    @PerActivity
    @Inject
    public Repository repository(ApiEndPoint endPoint,
                                 Executor executor, Context context){
        return new Repository(endPoint,executor,context);
    }


}
