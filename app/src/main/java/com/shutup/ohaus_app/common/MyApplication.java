package com.shutup.ohaus_app.common;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shutup on 16/9/18.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        // Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
