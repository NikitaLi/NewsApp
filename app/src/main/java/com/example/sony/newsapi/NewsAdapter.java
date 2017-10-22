package com.example.sony.newsapi;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sony.newsapi.model.Article;
import com.example.sony.newsapi.utils.DateUtils;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> newsArticles;

    public NewsAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Article newsArticle = newsArticles.get(position);
        Glide.with(holder.ivImage.getContext()).load(newsArticle.getUrlToImage())
                .into(holder.ivImage);
        holder.tvTitle.setText(newsArticle.getTitle());
        holder.tvTime.setText(DateUtils.formatNewsPublishedDate(newsArticle.getPublishedAt()));
        holder.tvContent.setText(newsArticle.getDescription());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArticleActivity.launch(view.getContext(), position);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvTime;
        TextView tvContent;

        NewsViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.card_news_image);
            tvTitle = itemView.findViewById(R.id.card_news_title);
            tvTime = itemView.findViewById(R.id.card_news_time);
            tvContent = itemView.findViewById(R.id.card_news_content);
        }
    }
}
