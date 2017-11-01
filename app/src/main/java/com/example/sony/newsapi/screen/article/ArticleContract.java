package com.example.sony.newsapi.screen.article;

public interface ArticleContract {

    interface View {

        void setTitleOfActionBar(int index);

        void openPage(String url);

        void showProgressBar();

        void hideProgressBar();

        void showToast(String message);
    }

    interface Presenter {

        void loadUrl(int index);

        void onPageStarted();

        void onPageFinished();

        void onReceivedError();
    }
}
