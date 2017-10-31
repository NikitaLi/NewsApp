package com.example.sony.newsapi.screen.article;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sony.newsapi.NewsStore;

class ArticlePresenter {

    private final ArticleView articleView;

    ArticlePresenter(ArticleView articleView) {
        this.articleView = articleView;
    }

    @SuppressLint("SetJavaScriptEnabled")
    void loadArticlePage(int index, WebView webView) {
        if (index != -1) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    articleView.showProgressBar();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    articleView.hideProgressBar();
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    articleView.hideProgressBar();
                    articleView.showToast("Не удалось загрузить статью");
                }
            });
            webView.loadUrl(NewsStore.getNewsArticles().get(index).getUrl());
            articleView.setTitleOfActionBar(index);
        } else {
            articleView.showToast("Incorrect index");
        }
    }
}
