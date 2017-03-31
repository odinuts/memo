package com.odinuts.memo.app;

import android.app.Application;

import com.odinuts.memo.model.Parent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createObject(Parent.class);
                    }})
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}