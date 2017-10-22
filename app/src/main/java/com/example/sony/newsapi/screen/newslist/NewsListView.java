package com.example.sony.newsapi.screen.newslist;

import com.example.sony.newsapi.model.GetArticlesResponse;

public interface NewsListView {

    void showTitle();

    void showNewsList(GetArticlesResponse articlesResponse);

    void showErrorToast();

}
