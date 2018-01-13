package com.example.sony.newsapi.screen.newslist;

import com.example.sony.newsapi.model.Article;

import java.util.List;

public interface NewsListContract {

    interface View {
        void showTitle();
        void hideSwipeRefreshLayout();
        void showNewsList(List<Article> newsList);
        void showErrorToast();
    }

    interface Presenter {
        void loadNewsList();
    }
}
