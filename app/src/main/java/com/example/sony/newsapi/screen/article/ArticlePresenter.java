package com.example.sony.newsapi.screen.article;

import com.example.sony.newsapi.NewsStore;

class ArticlePresenter implements ArticleContract.Presenter {

    private final ArticleContract.View articleView;

    ArticlePresenter(ArticleContract.View articleView) {
        this.articleView = articleView;
    }

    @Override
    public void loadUrl(int index) {
        if (index != -1) {
            articleView.setTitleOfActionBar(index);
            articleView.openPage(NewsStore.getNewsArticles().get(index).getUrl());
            return;
        }
        articleView.showToast("Incorrect index");
    }

    @Override
    public void onPageStarted() {
        articleView.showProgressBar();
    }

    @Override
    public void onPageFinished() {
        articleView.hideProgressBar();
    }

    @Override
    public void onReceivedError() {
        articleView.hideProgressBar();
        articleView.showToast("Не удалось загрузить статью");
    }
}
