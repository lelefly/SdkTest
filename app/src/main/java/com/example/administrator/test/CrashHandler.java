package com.example.administrator.test;

import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/29.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private Context context;
    private static CrashHandler instance;
    public static CrashHandler getInstance(){
        if(instance==null){
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.d("testlog", "uncaughtException, thread: " + thread + " name: " + thread.getName() + " id: " + thread.getId() + "exception: "
                + ex);
    }
}
