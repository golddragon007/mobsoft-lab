package com.example.marton.stephane.mobsoft_lab;

import android.app.Application;

import com.example.marton.stephane.mobsoft_lab.UI.UIModule;
import com.example.marton.stephane.mobsoft_lab.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Marton on 2017.03.27..
 */

public class MobSoftApplication extends Application {

    @Inject
    Repository repository;

    public static MobSoftApplicationComponent injector;

    public void setInjector(MobSoftApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerMobSoftApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();


        injector.inject(this);
        repository.open(getApplicationContext());
    }
}
