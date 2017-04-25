package com.example.marton.stephane.mobsoft_lab.interactor;

import dagger.Module;
import dagger.Provides;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.AnimeListItemsInteractor;
import com.example.marton.stephane.mobsoft_lab.interactor.login.LoginInteractor;

@Module
public class InteractorModule {


    @Provides
    public AnimeListItemsInteractor provideAnimeListItems() {
        return new AnimeListItemsInteractor();
    }

    @Provides
    public LoginInteractor provideLogin() {
        return new LoginInteractor();
    }

}
