package com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.marton.stephane.mobsoft_lab.UI.Presenter;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.CommentsInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.events.GetCommentsEvent;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.utils.CacheSystem;
import com.example.marton.stephane.mobsoft_lab.utils.Connectivity;
import com.example.marton.stephane.mobsoft_lab.utils.DatabaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Marton on 2017.03.27..
 */

public class AnimeWatchPresenter extends Presenter<AnimeWatchScreen> {

    @Inject
    CommentsInteractor commentsInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public AnimeWatchPresenter() {
    }

    @Override
    public void attachScreen(AnimeWatchScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void StartDownload(String id, final AnimeWatch animeWatch) {
        String PrefFileName = "AniDBAppPrefs";

        SharedPreferences settings = animeWatch.getSharedPreferences(PrefFileName, 0);
        Boolean onlyWifiPref = settings.getBoolean("onlyWifi", false);
        boolean downloadEnabled = Connectivity.isConnectedWifi(animeWatch) || !Connectivity.isConnectedWifi(animeWatch) && !onlyWifiPref;
        CacheSystem cs = new CacheSystem(animeWatch, "anime", downloadEnabled, Connectivity.isConnected(animeWatch), new DatabaseController(animeWatch), id);
        cs.setOnCacheListener(new CacheSystem.OnCacheListener() {
            @Override
            public void onCacheSuccess(final Object object) {
                animeWatch.onCacheSuccess(object);
            }

            @Override
            public void onCacheImageReady(final String id) {
                animeWatch.onCacheImageReady(id);
            }

            @Override
            public void onNewHotAnime() {

            }

            @Override
            public void onCacheFailed(final String message) {
                animeWatch.showMessage(message);
            }
        });

        cs.start();
    }

    public void getComments(final String id) {
        /*executor.execute(new Runnable() {
            @Override
            public void run() {
                commentsInteractor.getComments(id);
            }
        });*/
    }

    public void onEventMainThread(GetCommentsEvent event) {
        Log.d("test","test");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading comments", event.getThrowable());
        } else {
            if (screen != null) {
                List<Comment> comments = event.getComments();
                if (comments.size() > 0) {
                    screen.receiveComments(new ArrayList<Comment>(comments));
                }
                else {
                    screen.showMessage("No comments available!");
                }
            }
        }
    }
}
