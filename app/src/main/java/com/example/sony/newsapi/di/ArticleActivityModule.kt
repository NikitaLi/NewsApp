package com.example.sony.newsapi.di

import com.example.sony.newsapi.repository.Repository
import com.example.sony.newsapi.repository.RepositoryImpl
import com.example.sony.newsapi.screen.article.ArticleContract
import com.example.sony.newsapi.screen.article.ArticlePresenter
import dagger.Module
import dagger.Provides

@Module
class ArticleActivityModule(private val view: ArticleContract.View) {

    @Provides
    fun providePresenter(repository: Repository): ArticleContract.Presenter =
        ArticlePresenter(view, repository)

    @Provides
    fun provideRepository(): Repository = RepositoryImpl()
}