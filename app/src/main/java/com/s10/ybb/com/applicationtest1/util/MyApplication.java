package com.s10.ybb.com.applicationtest1.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/2/7.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
