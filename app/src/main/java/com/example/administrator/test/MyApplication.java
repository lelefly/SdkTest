package com.example.administrator.test;

import android.app.Application;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
