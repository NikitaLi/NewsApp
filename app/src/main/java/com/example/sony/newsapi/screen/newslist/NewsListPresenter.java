package com.example.sony.newsapi.screen.newslist;

import android.support.annotation.NonNull;

import com.example.sony.newsapi.Config;
import com.example.sony.newsapi.repository.Repository;
import com.example.sony.newsapi.repository.RepositoryImpl;
import com.example.sony.newsapi.model.Article;
import com.example.sony.newsapi.model.ArticlesResponse;
import com.example.sony.newsapi.networking.NewsAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class NewsListPresenter implements NewsListContract.Presenter {

    private final NewsListContract.View view;
    private Repository repository;

    NewsListPresenter(NewsListContract.View newsListView) {
        this.view = newsListView;
        this.repository = new RepositoryImpl();
    }

    @Override
    public void loadNewsList() {
        List<Article> articles = repository.loadFromDB();
        view.showNewsList(articles);
        Call<ArticlesResponse> call = NewsAPI.getApi().getArticles(
                Config.API_SOURCE,
                Config.API_NEWS_SORT_BY
        );
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
                ArticlesResponse articlesResponse = response.body();
                if (articlesResponse != null) {
                    List<Article> newsList = articlesResponse.getArticles();
                    repository.saveToDB(newsList);
                    view.showNewsList(repository.loadFromDB());
                    view.hideSwipeRefreshLayout();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {
                view.hideSwipeRefreshLayout();
                view.showErrorToast();
            }
        });
    }
}
