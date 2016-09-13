package com.gkzxhn.ywt_gkzx;

import android.app.Application;

import com.gkzxhn.ywt_gkzx.utils.CrashHandler;

/**
 * Created by ZengWenZhi on 2016/8/16 0016.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(this);
    }
}
