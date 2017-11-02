package com.example.sony.newsapi.screen.newslist;

import android.support.annotation.NonNull;

import com.example.sony.newsapi.Config;
import com.example.sony.newsapi.NewsStore;
import com.example.sony.newsapi.model.Article;
import com.example.sony.newsapi.model.GetArticlesResponse;
import com.example.sony.newsapi.networking.NewsAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class NewsListPresenter {

    private final NewsListView view;

    NewsListPresenter(NewsListView newsListView) {
        this.view = newsListView;
    }

    void loadNewsList() {
        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles(
                Config.API_SOURCE,
                Config.API_NEWS_SORT_BY
        );
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetArticlesResponse> call, @NonNull Response<GetArticlesResponse> response) {
                GetArticlesResponse articlesResponse = response.body();
                if (articlesResponse != null) {
                    List<Article> newsList = articlesResponse.getArticles();
                    NewsStore.setNewsArticles(newsList);
                    view.showNewsList(newsList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetArticlesResponse> call, @NonNull Throwable t) {
                view.showErrorToast();
            }
        });
    }
}
