package com.example.sony.newsapi.screen.article

interface ArticleContract {

    interface View {
        fun openPage(url: String)

        fun showTitleOfActionBar(title: String)
        fun showProgressBar()
        fun hideProgressBar()
        fun showToast(message: String)
    }

    interface Presenter {
        fun loadUrl(index: Int)

        fun onPageStarted()
        fun onPageFinished()
        fun onReceivedError()
    }
}
