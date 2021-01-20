package com.example.infosec.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infosec.R;
import com.example.infosec.model.api.response.NewsResponse;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<NewsResponse.ArticlesBean> articlesBeans;
    Context context;


    public NewsAdapter(List<NewsResponse.ArticlesBean> articlesBeans, Context context) {
        this.articlesBeans = articlesBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        NewsResponse.ArticlesBean articlesBean=articlesBeans.get(position);

        holder.title.setText(articlesBean.getTitle());
        holder.describtion.setText(articlesBean.getDescription());
        Glide.with(context)
                .load(articlesBean.getUrlToImage())
                .fitCenter()
                .into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return articlesBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,describtion;
        ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            describtion=itemView.findViewById(R.id.describtion);
            newsImage=itemView.findViewById(R.id.newsimage);
        }
    }
}
