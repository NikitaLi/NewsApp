package com.example.sony.newsapi.di

import com.example.sony.newsapi.repository.Repository
import com.example.sony.newsapi.repository.RepositoryImpl
import com.example.sony.newsapi.screen.newslist.NewsListContract
import com.example.sony.newsapi.screen.newslist.NewsListPresenter
import dagger.Module
import dagger.Provides

@Module
class NewsListActivityModule(private val view: NewsListContract.View) {

    @Provides
    fun providePresenter(repository: Repository): NewsListContract.Presenter =
        NewsListPresenter(view, repository)

    @Provides
    fun provideRepository(): Repository = RepositoryImpl()
}