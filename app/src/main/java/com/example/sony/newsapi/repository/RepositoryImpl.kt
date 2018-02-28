package com.example.sony.newsapi.repository

import com.example.sony.newsapi.model.Article

import io.realm.Realm

class RepositoryImpl : Repository {
    override fun saveToDB(articles: List<Article>) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1 -> realm1.copyToRealmOrUpdate(articles) }
        realm.close()
    }

    override fun loadFromDB(): List<Article> {
        val realm = Realm.getDefaultInstance()
        val articles = realm.copyFromRealm(realm.where(Article::class.java).findAll())
        realm.close()
        return articles
    }

    override fun loadFromApi(): List<Article> {
        TODO("not implemented")
    }
}
