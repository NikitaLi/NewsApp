package com.example.sony.newsapi.di

import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {
    fun createNewsListComponent(module: NewsListActivityModule): NewsListActivityComponent
    fun createArticleComponent(module: ArticleActivityModule): ArticleActivityComponent
}