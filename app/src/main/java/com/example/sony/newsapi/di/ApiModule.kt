package com.example.sony.newsapi.di

import com.example.sony.newsapi.networking.NewsAPI
import com.example.sony.newsapi.networking.NewsAPI.NewsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiService() : NewsService {
        return NewsAPI.api
    }
}