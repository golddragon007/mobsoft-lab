package com.example.marton.stephane.mobsoft_lab.UI.Main;

import android.content.SharedPreferences;

import com.example.marton.stephane.mobsoft_lab.UI.Presenter;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.CommentsInteractor;
import com.example.marton.stephane.mobsoft_lab.utils.CacheSystem;
import com.example.marton.stephane.mobsoft_lab.utils.Connectivity;
import com.example.marton.stephane.mobsoft_lab.utils.DatabaseController;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Marton on 2017.03.27..
 */

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    CommentsInteractor commentsInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void StartDownload(final MainActivity mainActivity){
        String PrefFileName = "AniDBAppPrefs";

        SharedPreferences settings = mainActivity.getSharedPreferences(PrefFileName, 0);
        Boolean onlyWifiPref = settings.getBoolean("onlyWifi", false);
        boolean downloadEnabled = Connectivity.isConnectedWifi(mainActivity) || !Connectivity.isConnectedWifi(mainActivity) && !onlyWifiPref;
        CacheSystem cs = new CacheSystem(mainActivity, "main", downloadEnabled, Connectivity.isConnected(mainActivity), new DatabaseController(mainActivity));
        cs.setOnCacheListener(new CacheSystem.OnCacheListener() {
            @Override
            public void onCacheSuccess(final Object obj) {
                mainActivity.onCacheSuccess(obj);
            }

            @Override
            public void onCacheImageReady(String id) {
                mainActivity.onCacheImageReady(id);
            }

            @Override
            public void onNewHotAnime() {

            }

            @Override
            public void onCacheFailed(final String message) {
                mainActivity.showMessage(message);
            }
        });

        cs.start();
    }
}
