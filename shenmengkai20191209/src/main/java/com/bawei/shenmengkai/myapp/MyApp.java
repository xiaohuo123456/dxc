package com.bawei.shenmengkai.myapp;

import android.app.Application;
import android.content.Context;

import com.bawei.shenmengkai.dao.DaoMaster;
import com.bawei.shenmengkai.dao.DaoSession;

public class MyApp extends Application {
    public static Context context;
    public static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        session = DaoMaster.newDevSession(this, "weektwo");
    }
    public static DaoSession getDaoSession(){
        return session;
    }
}
