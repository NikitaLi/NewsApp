package com.example.sony.newsapi.repository;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MigrationDB implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.create("TaskRealmModel")
                    .addField("title", String.class);
            oldVersion++;
        }
    }
}
