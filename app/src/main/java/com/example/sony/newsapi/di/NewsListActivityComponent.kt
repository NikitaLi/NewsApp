package com.example.sony.newsapi.di

import com.example.sony.newsapi.screen.newslist.NewsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [NewsListActivityModule::class])
interface NewsListActivityComponent {
    fun inject(newsListActivity: NewsListActivity)
}