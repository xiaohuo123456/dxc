package com.bawei.myapplication.app;

import android.app.Application;

import com.bawei.myapplication.bao2.DaoMaster;
import com.bawei.myapplication.bao2.DaoSession;

public class MyApp extends Application {

    private static DaoSession session;

    @Override
    public void onCreate() {

        super.onCreate();
        session = DaoMaster.newDevSession(this,"dxcc.db");

    }
    public static DaoSession getSession(){
        return session;
    }
}
