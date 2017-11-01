package com.example.sony.newsapi.screen.article;

import com.example.sony.newsapi.NewsStore;

class ArticlePresenter {

    private final ArticleView articleView;

    ArticlePresenter(ArticleView articleView) {
        this.articleView = articleView;
    }

    String getArticleUrl(int index) {
        if (index != -1) {
            articleView.setTitleOfActionBar(index);
            return NewsStore.getNewsArticles().get(index).getUrl();
        }
        articleView.showToast("Incorrect index");
        return null;
    }

    void onPageStarted() {
        articleView.showProgressBar();
    }

    void onPageFinished() {
        articleView.hideProgressBar();
    }

    void onReceivedError() {
        articleView.hideProgressBar();
        articleView.showToast("Не удалось загрузить статью");
    }
}
