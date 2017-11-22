package com.example.sony.newsapi;

import android.app.Application;

import com.example.sony.newsapi.repository.MigrationDB;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new MigrationDB())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
