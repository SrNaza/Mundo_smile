package com.example.tecomca.mylogin_seccion05.App;

import android.app.Application;
import android.os.SystemClock;

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(1000);
    }
}
