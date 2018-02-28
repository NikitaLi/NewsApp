package com.example.sony.newsapi

import android.app.Application

import com.example.sony.newsapi.repository.MigrationDB

import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .schemaVersion(1)
            .migration(MigrationDB())
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
