package com.example.sony.newsapi.di

import com.example.sony.newsapi.screen.article.ArticleActivity
import dagger.Subcomponent

@Subcomponent(modules = [ArticleActivityModule::class])
interface ArticleActivityComponent {
    fun inject(articleActivity: ArticleActivity)
}