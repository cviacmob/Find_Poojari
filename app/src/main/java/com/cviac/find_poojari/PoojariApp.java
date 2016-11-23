package com.cviac.find_poojari;

import android.app.Application;
import android.content.ContextWrapper;

/**
 * Created by User on 11/23/2016.
 */

public class PoojariApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
