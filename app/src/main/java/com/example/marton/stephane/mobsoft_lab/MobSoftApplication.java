package com.example.marton.stephane.mobsoft_lab;

import android.app.Application;

import com.example.marton.stephane.mobsoft_lab.UI.UIModule;

/**
 * Created by Marton on 2017.03.27..
 */

public class MobSoftApplication extends Application {

    public static MobSoftApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerMobSoftApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
