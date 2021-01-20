package com.example.infosec.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.infosec.R;
import com.example.infosec.di.component.DaggerAppComponent;
import com.example.infosec.di.module.AppModule;
import com.example.infosec.di.module.NetworkModule;
import com.example.infosec.model.api.response.NewsResponse;
import com.example.infosec.utill.Utills;
import com.example.infosec.view.adapter.NewsAdapter;
import com.example.infosec.viewmodel.AppViewModel;
import com.example.infosec.viewmodel.ViewModelFactory;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.infosec.utill.Utills.isNetworkAvailable;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    RecyclerView feed;

    @Inject
    CompositeDisposable compositeDisposable;

    AppViewModel appViewModel;

    TextView errorTxt;
    MaterialButton btnAgain;

    ShimmerFrameLayout shimmerFrameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Home");
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build()
                .appComponent(new NetworkModule("http://newsapi.org/v2/"))
                .inject(this);


        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);

        feed=findViewById(R.id.feed);
        shimmerFrameLayout=findViewById(R.id.shimmerFrameLayout);
        errorTxt=findViewById(R.id.error_txt);
        btnAgain=findViewById(R.id.btn_again);

        getNewsFeed();

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewsFeed();

            }
        });

    }


    void getNewsFeed(){
        if (isNetworkAvailable(this)){
            shimmerFrameLayout.startShimmerAnimation();
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            feed.setVisibility(View.GONE);
            btnAgain.setVisibility(View.GONE);
            errorTxt.setVisibility(View.GONE);
            compositeDisposable.add(
                    appViewModel.getNewsFeed()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<NewsResponse>() {
                                @Override
                                public void accept(NewsResponse newsResponse) throws Exception {
                                    if (!newsResponse.getArticles().isEmpty()&& newsResponse.getArticles().size()>0){
                                        shimmerFrameLayout.stopShimmerAnimation();
                                        shimmerFrameLayout.setVisibility(View.GONE);
                                        feed.setVisibility(View.VISIBLE);
                                        NewsAdapter newsAdapter= new NewsAdapter(newsResponse.getArticles(),MainActivity.this);
                                        feed.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        feed.setAdapter(newsAdapter);
                                    }else{
                                        shimmerFrameLayout.stopShimmerAnimation();
                                        shimmerFrameLayout.setVisibility(View.GONE);
                                        feed.setVisibility(View.GONE);
                                        btnAgain.setVisibility(View.VISIBLE);
                                        errorTxt.setVisibility(View.VISIBLE);
                                    }

                                    Log.i("TAG", "accept: "+ newsResponse.getArticles().get(0).getTitle());
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    feed.setVisibility(View.GONE);
                                    btnAgain.setVisibility(View.VISIBLE);
                                    errorTxt.setVisibility(View.VISIBLE);
                                    Log.i("TAG", "accept: "+throwable.getMessage());
                                }
                            })
            );
        }else{

            shimmerFrameLayout.setVisibility(View.GONE);
            feed.setVisibility(View.GONE);
            btnAgain.setVisibility(View.VISIBLE);
            errorTxt.setVisibility(View.VISIBLE);
        }



    }

    @Override
    public void onStop() {
        super.onStop(); if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }

    }
}
