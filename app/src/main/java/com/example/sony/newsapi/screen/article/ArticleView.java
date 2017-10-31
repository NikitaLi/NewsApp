package com.example.sony.newsapi.screen.article;

public interface ArticleView {

    void setTitleOfActionBar(int index);

    void showProgressBar();

    void hideProgressBar();

    void showToast(String message);
}
