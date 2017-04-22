package com.example.marton.stephane.mobsoft_lab.UI;

import android.content.Context;

import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatch;
import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatchPresenter;
import com.example.marton.stephane.mobsoft_lab.UI.Main.MainPresenter;
import com.example.marton.stephane.mobsoft_lab.UI.Options.Options;
import com.example.marton.stephane.mobsoft_lab.UI.Options.OptionsPresenter;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.AnimeListItemsInteractor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by Marton on 2017.03.27..
 */

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public AnimeWatchPresenter provideAnimeWatchPresenter() {
        return new AnimeWatchPresenter();
    }

    @Provides
    @Singleton
    public OptionsPresenter provideOptionsPresenter() {
        return new OptionsPresenter();
    }

    @Provides
    @Singleton
    public AnimeListItemsInteractor provideAnimeListItemsInteractor() {
        return new AnimeListItemsInteractor();
    }


    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}
