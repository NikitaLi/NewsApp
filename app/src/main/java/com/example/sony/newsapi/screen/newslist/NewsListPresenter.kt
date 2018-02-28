package com.example.sony.newsapi.screen.newslist

import com.example.sony.newsapi.Config
import com.example.sony.newsapi.networking.NewsAPI
import com.example.sony.newsapi.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class NewsListPresenter(
	private val view: NewsListContract.View,
	private val repository: Repository
) : NewsListContract.Presenter {

	override fun loadNewsList() {
		val articles = repository.loadFromDB()
		view.showNewsList(articles)

		NewsAPI.getApi()
			.getArticles(Config.API_SOURCE, Config.API_NEWS_SORT_BY)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.map { it.body() }
			.map { it.articles }
			.doOnNext { view.hideSwipeRefreshLayout() }
			.doOnNext { view.showNewsList(repository.loadFromDB()) }
			.doOnError { view.hideSwipeRefreshLayout() }
			.doOnError { view.showErrorToast() }
			.subscribe(repository::saveToDB, { t: Throwable -> t.printStackTrace() })
	}
}
