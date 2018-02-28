package com.example.sony.newsapi.repository

import io.realm.DynamicRealm
import io.realm.RealmMigration

class MigrationDB : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        var oldVersion = oldVersion
        val schema = realm.schema

        if (oldVersion == 0L) {
            schema.create("TaskRealmModel")
                .addField("title", String::class.java)
            oldVersion++
        }
    }
}
