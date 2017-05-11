package com.example.marton.stephane.mobsoft_lab;

import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatch;
import com.example.marton.stephane.mobsoft_lab.UI.AnimeWatch.AnimeWatchPresenter;
import com.example.marton.stephane.mobsoft_lab.UI.Main.MainActivity;
import com.example.marton.stephane.mobsoft_lab.UI.Main.MainPresenter;
import com.example.marton.stephane.mobsoft_lab.UI.Options.Options;
import com.example.marton.stephane.mobsoft_lab.UI.Options.OptionsPresenter;
import com.example.marton.stephane.mobsoft_lab.UI.UIModule;
import com.example.marton.stephane.mobsoft_lab.interactor.InteractorModule;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.CommentsInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.login.LoginInteractor;
import com.example.marton.stephane.mobsoft_lab.network.NetworkModule;
import com.example.marton.stephane.mobsoft_lab.repository.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by Marton on 2017.03.27..
 */


@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, NetworkModule.class})
public interface MobSoftApplicationComponent {
    void inject(MobSoftApplication mobSoftApplication);


    void inject(MainPresenter mainPresenter);
    void inject(MainActivity mainActivity);

    void inject(AnimeWatchPresenter animeWatchPresenter);
    void inject(AnimeWatch animeWatch);

    void inject(OptionsPresenter optionsPresenter);
    void inject(Options options);


    void inject(CommentsInteractor CommentsInteractor);
    void inject(LoginInteractor loginInteractor);
}
