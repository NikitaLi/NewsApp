package com.example.sony.newsapi.screen.newslist

import com.example.sony.newsapi.model.Article

interface NewsListContract {

    interface View {
        fun showTitle()
        fun hideSwipeRefreshLayout()
        fun showNewsList(newsList: List<Article>)
        fun showErrorToast()
    }

    interface Presenter {
        fun loadNewsList()
    }
}
