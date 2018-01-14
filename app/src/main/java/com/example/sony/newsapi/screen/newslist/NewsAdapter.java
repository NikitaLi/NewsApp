package com.example.sony.newsapi.screen.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sony.newsapi.R;
import com.example.sony.newsapi.model.Article;
import com.example.sony.newsapi.screen.article.ArticleActivity;
import com.example.sony.newsapi.utils.DateUtils;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> newsArticles;

    NewsAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        Article newsArticle = newsArticles.get(position);
        holder.tvCardTitle.setText(newsArticle.getTitle());
        holder.tvCardTime.setText(DateUtils.formatNewsPublishedDate(newsArticle.getPublishedAt()));
        holder.tvCardContent.setText(newsArticle.getDescription());
        Glide.with(holder.ivCardImage.getContext())
                .load(newsArticle.getUrlToImage())
                .into(holder.ivCardImage);
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArticleActivity.launch(view.getContext(), holder.getAdapterPosition());
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCardImage;
        TextView tvCardTitle;
        TextView tvCardTime;
        TextView tvCardContent;

        NewsViewHolder(View itemView) {
            super(itemView);
            ivCardImage = itemView.findViewById(R.id.iv_card_news_image);
            tvCardTitle = itemView.findViewById(R.id.tv_card_news_title);
            tvCardTime = itemView.findViewById(R.id.tv_card_news_time);
            tvCardContent = itemView.findViewById(R.id.tv_card_news_content);
        }
    }
}
