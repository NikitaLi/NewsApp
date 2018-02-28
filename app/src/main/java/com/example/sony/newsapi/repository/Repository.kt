package com.example.sony.newsapi.repository

import com.example.sony.newsapi.model.Article

interface Repository {
    fun saveToDB(articles: List<Article>)
    fun loadFromDB(): List<Article>
    fun loadFromApi(): List<Article>
}
