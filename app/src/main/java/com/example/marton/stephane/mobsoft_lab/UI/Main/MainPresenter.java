package com.example.marton.stephane.mobsoft_lab.UI.Main;

import com.example.marton.stephane.mobsoft_lab.UI.Presenter;

/**
 * Created by Marton on 2017.03.27..
 */

public class MainPresenter extends Presenter<MainScreen> {

    private static MainPresenter instance = null;

    private MainPresenter() {
    }

    public static MainPresenter getInstance() {
        if (instance == null) {
            instance = new MainPresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}
