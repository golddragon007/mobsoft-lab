package com.example.marton.stephane.mobsoft_lab;

import com.example.marton.stephane.mobsoft_lab.UI.Main.MainActivity;
import com.example.marton.stephane.mobsoft_lab.UI.UIModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by Marton on 2017.03.27..
 */


@Singleton
@Component(modules = {UIModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);

}
