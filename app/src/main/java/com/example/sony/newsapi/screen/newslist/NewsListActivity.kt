package com.example.sony.newsapi.screen.newslist

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

import com.example.sony.newsapi.Config
import com.example.sony.newsapi.R
import com.example.sony.newsapi.model.Article
import com.example.sony.newsapi.repository.RepositoryImpl

class NewsListActivity : AppCompatActivity(), NewsListContract.View,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var rvNews: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var presenter: NewsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showTitle()

        rvNews = findViewById(R.id.rv_news)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rvNews.layoutManager = layoutManager

        presenter = NewsListPresenter(this, RepositoryImpl())
        presenter.loadNewsList()

        swipeRefreshLayout = findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
    }

    override fun showTitle() {
        title = Config.SOURCE_NAME
    }

    override fun hideSwipeRefreshLayout() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showNewsList(newsList: List<Article>) {
        val newsAdapter = NewsAdapter(newsList)
        rvNews.adapter = newsAdapter
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Нет сети", Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        presenter.loadNewsList()
        rvNews.smoothScrollToPosition(rvNews.adapter.itemCount - 1)
    }
}