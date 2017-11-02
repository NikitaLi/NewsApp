package com.example.sony.newsapi.screen.article;

public interface ArticleContract {

    interface View {
        void openPage(String url);

        void showTitleOfActionBar(String title);
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
