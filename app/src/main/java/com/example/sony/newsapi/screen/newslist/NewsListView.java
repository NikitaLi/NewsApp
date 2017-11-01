package com.example.sony.newsapi.screen.newslist;

import com.example.sony.newsapi.model.Article;

import java.util.List;

public interface NewsListView {

    void showTitle();

    void showNewsList(List<Article> newsList);

    void showErrorToast();

}
