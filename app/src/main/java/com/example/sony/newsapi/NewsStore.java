package com.example.sony.newsapi;

import com.example.sony.newsapi.model.Article;

import java.util.ArrayList;
import java.util.List;

class NewsStore {
    private static List<Article> newsArticles = new ArrayList<>();

    static List<Article> getNewsArticles() {
        return newsArticles;
    }

    static void setNewsArticles(List<Article> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }
}
