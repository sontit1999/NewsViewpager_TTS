package com.example.newsviewpager_tts;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test","OncreatMyapplication");
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
