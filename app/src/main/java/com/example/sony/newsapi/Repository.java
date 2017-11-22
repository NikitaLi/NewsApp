package com.example.sony.newsapi;

import com.example.sony.newsapi.model.Article;

import java.util.List;

public interface Repository {
    void saveToDB(List<Article> articles);
    List<Article> loadFromDB();
    List<Article> loadFromApi();
}
