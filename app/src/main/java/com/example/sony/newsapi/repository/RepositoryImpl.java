package com.example.sony.newsapi.repository;

import android.support.annotation.NonNull;

import com.example.sony.newsapi.model.Article;

import java.util.List;

import io.realm.Realm;

public class RepositoryImpl implements Repository {
    @Override
    public void saveToDB(final List<Article> articles) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealmOrUpdate(articles);
            }
        });
        realm.close();
    }

    @Override
    public List<Article> loadFromDB() {
        Realm realm = Realm.getDefaultInstance();
        List<Article> articles = realm.copyFromRealm(realm.where(Article.class).findAll());
        realm.close();
        return articles;
    }

    @Override
    public List<Article> loadFromApi() {
        return null;
    }
}
