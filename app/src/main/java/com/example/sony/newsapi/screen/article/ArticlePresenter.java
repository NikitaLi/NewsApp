package com.example.sony.newsapi.screen.article;

import com.example.sony.newsapi.RepositoryImpl;
import com.example.sony.newsapi.model.Article;

class ArticlePresenter implements ArticleContract.Presenter {

    private final ArticleContract.View view;
    private RepositoryImpl repository;

    ArticlePresenter(ArticleContract.View articleView) {
        this.view = articleView;
        this.repository = new RepositoryImpl();
    }

    @Override
    public void loadUrl(int index) {
        if (index != -1) {
            Article article = repository.loadFromDB().get(index);
            view.showTitleOfActionBar(article.getTitle());
            view.openPage(article.getUrl());
            return;
        }
        view.showToast("Incorrect index");
    }

    @Override
    public void onPageStarted() {
        view.showProgressBar();
    }

    @Override
    public void onPageFinished() {
        view.hideProgressBar();
    }

    @Override
    public void onReceivedError() {
        view.hideProgressBar();
        view.showToast("Не удалось загрузить статью");
    }
}
