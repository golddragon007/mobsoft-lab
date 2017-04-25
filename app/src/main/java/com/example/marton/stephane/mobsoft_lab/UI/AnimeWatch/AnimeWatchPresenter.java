package com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch;

import android.util.Log;

import com.example.marton.stephane.mobsoft_lab.UI.Main.MainScreen;
import com.example.marton.stephane.mobsoft_lab.UI.Presenter;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.AnimeListItemsInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.events.GetAnimeListItemsEvent;
import com.example.marton.stephane.mobsoft_lab.models.AnimeListItem;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by Marton on 2017.03.27..
 */

public class AnimeWatchPresenter extends Presenter<AnimeWatchScreen> {

    @Inject
    AnimeListItemsInteractor animeListItemsInteractor;

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

    public void getAnimeListItems() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                animeListItemsInteractor.getAnimeListItems();
            }
        });
    }

    public void onEventMainThread(GetAnimeListItemsEvent event) {
        Log.d("test","test");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage("error");
            }
            Log.e("Networking", "Error reading favourites", event.getThrowable());
        } else {
            if (screen != null) {
                List<AnimeListItem> animeListItems = event.getAnimeListItems();
                if (animeListItems.size() > 0) {
                    screen.showMessage("Ok!");
                }
                else {
                    screen.showMessage("No list available!");
                }
            }
        }
    }
}
