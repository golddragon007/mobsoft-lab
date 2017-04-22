package com.example.marton.stephane.mobsoft_lab.interactor;

import dagger.Module;
import dagger.Provides;
import com.example.marton.stephane.mobsoft_lab.interactor.animelistitem.AnimeListItemsInteractor;

@Module
public class InteractorModule {


    @Provides
    public AnimeListItemsInteractor provideFavourites() {
        return new AnimeListItemsInteractor();
    }


}
