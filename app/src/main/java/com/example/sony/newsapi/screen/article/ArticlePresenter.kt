package com.example.sony.newsapi.screen.article

import com.example.sony.newsapi.repository.Repository

internal class ArticlePresenter(
    private val view: ArticleContract.View,
    private val repository: Repository
) : ArticleContract.Presenter {

    override fun loadUrl(index: Int) {
        if (index != -1) {
            val article = repository.loadFromDB()[index]
            view.showTitleOfActionBar(article.title)
            view.openPage(article.url)
            return
        }
        view.showToast("Incorrect index")
    }

    override fun onPageStarted() {
        view.showProgressBar()
    }

    override fun onPageFinished() {
        view.hideProgressBar()
    }

    override fun onReceivedError() {
        view.hideProgressBar()
        view.showToast("Не удалось загрузить статью")
    }
}
